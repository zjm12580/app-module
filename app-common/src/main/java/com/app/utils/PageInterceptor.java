package com.app.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;


import com.app.emum.CompareTypeEnum;
import com.app.emum.JavaClassEnum;
import com.app.entity.PageParameter;
import com.app.entity.Property;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.jdbc.ConnectionLogger;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 分页查询时把List放入参数page中并返回
 * </p>
 *
 * @author dixingxing
 * @date 2012-7-12
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class})

})
public class PageInterceptor implements Interceptor {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    //存储所有语句名称
    HashMap<String, String> map_statement = new HashMap<String, String>();
    //用户提供分页计算条数后缀
    static final String COUNT_ID = "_count";

    /**
     * 获取所有statement语句的名称
     * <p>
     *
     * @param configuration
     */
    protected synchronized void initStatementMap(Configuration configuration) {
        if (!map_statement.isEmpty()) {
            return;
        }
        Collection<String> statements = configuration.getMappedStatementNames();
        for (Iterator<String> iter = statements.iterator(); iter.hasNext(); ) {
            String element = iter.next();
            map_statement.put(element, element);
        }
    }

    /**
     * 获取数据库连接
     * <p>
     *
     * @param transaction
     * @param statementLog
     * @return
     * @throws SQLException
     */
    protected Connection getConnection(Transaction transaction, Log statementLog) throws SQLException {
        Connection connection = transaction.getConnection();
        if (statementLog.isDebugEnabled()) {
            return ConnectionLogger.newInstance(connection, statementLog, 0);
        } else {
            return connection;
        }
    }

    public Object intercept(Invocation invocation) throws Throwable {
        Object parameter = invocation.getArgs()[1];
        PageParameter page = seekPage(parameter);
        if (page == null) {
            return invocation.proceed();
        } else {
            return handlePaging(invocation, parameter, page);
        }

    }

    /**
     * 处理分页的情况
     * <p>
     *
     * @param invocation
     * @param parameter
     * @param page
     * @throws SQLException
     */
    @SuppressWarnings("rawtypes")
    protected List handlePaging(Invocation invocation, Object parameter, PageParameter page) throws Exception {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Configuration configuration = mappedStatement.getConfiguration();
        if (map_statement.isEmpty()) {
            initStatementMap(configuration);
        }
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        // 查询结果集
        StaticSqlSource sqlsource = new StaticSqlSource(configuration, getLimitString(boundSql.getSql(), page),
                boundSql.getParameterMappings());
        MappedStatement.Builder builder = new MappedStatement.Builder(configuration, "id_temp_result", sqlsource,
                SqlCommandType.SELECT);
        builder.resultMaps(mappedStatement.getResultMaps()).resultSetType(mappedStatement.getResultSetType())
                .statementType(mappedStatement.getStatementType());
        MappedStatement query_statement = builder.build();

        List data = (List) exeQuery(invocation, query_statement);
        //设置到page对象
        page.setRecords(data);
        page.setCount(getTotalSize(invocation, configuration, mappedStatement, boundSql, parameter, page));

        return data;
    }

    /**
     * 根据提供的语句执行查询操作
     * <p>
     *
     * @param invocation
     * @param query_statement
     * @return
     * @throws Exception
     */
    protected Object exeQuery(Invocation invocation, MappedStatement query_statement) throws Exception {
        Object[] args = invocation.getArgs();
        return invocation.getMethod().invoke(invocation.getTarget(),
                new Object[]{query_statement, args[1], args[2], args[3]});
    }

    /**
     * 获取总记录数量
     * <p>
     *
     * @param configuration
     * @param mappedStatement
     * @param parameter
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("rawtypes")
    protected int getTotalSize(Invocation invocation, Configuration configuration, MappedStatement mappedStatement,
                               BoundSql boundSql, Object parameter, PageParameter page) throws Exception {

        String count_id = mappedStatement.getId() + COUNT_ID;
        int totalSize = 0;
        if (map_statement.containsKey(count_id)) {
            // 优先查找能统计条数的sql
            List data = (List) exeQuery(invocation, mappedStatement.getConfiguration().getMappedStatement(count_id));
            if (data.size() > 0) {
                totalSize = Integer.parseInt(data.get(0).toString());
            }
        } else {
            Executor exe = (Executor) invocation.getTarget();
            Connection connection = getConnection(exe.getTransaction(), mappedStatement.getStatementLog());
            String countSql = getCountSql(boundSql.getSql(), parameter, page);
            totalSize = getTotalSize(configuration, mappedStatement, boundSql, countSql, connection, parameter);
        }

        return totalSize;
    }

    /**
     * 拼接查询sql,加入分页条件
     * <p>
     *
     * @param sql
     * @param page
     */
    protected String getLimitString(String sql, PageParameter page) throws IllegalAccessException {
        List<Property> properties = page.getProperties();
        if (properties == null || properties.size() == 0) {
            return sql;
        }

        StringBuilder sb = new StringBuilder(sql.length() + 100);

        sb.append("SELECT tb.* FROM (").append(sql).append(") tb WHERE 1=1");
        sql = jointCondition(sb, properties);
        sql += " limit " + page.getStart() + " ," + page.getPageSize();
        logger.info("查询-------------------------：\n" + sql);
        System.out.println("查询-------------------------：\n" + sql);
        return sql;

    }

    /**
     * 设置查询条件
     *
     * @param sb
     * @param properties
     * @return
     */
    private String setCondition(StringBuilder sb, List<Property> properties) {

        for (int i = 0; i < properties.size(); i++) {
            Property property = properties.get(i);
            sb.append(" tb.").append(property.getName());
            if (property.getType().equals(JavaClassEnum.STRING.getDesc())) {
                sb.append(" = '").append(property.getValue() + "'");
            }
            if (property.getType().equals(JavaClassEnum.INTEGER.getDesc())) {
                Integer value = Integer.parseInt(property.getValue());
                sb.append(" = ").append(value);
            }
            if (property.getType().equals(JavaClassEnum.DATE.getDesc())) {
                if (property.getCompareType().equals("equals")) {
                    sb.append("=").append(property.getValue());
                }
            }
            if (i != (properties.size() - 1)) {
                sb.append("AND");
            }

        }
        return sb.toString();
    }

    /**
     * 加入拼接条件
     *
     * @time 2017年1月11日
     * @author 朱佳敏
     * @year 2017
     * @remark
     */
    private String jointCondition(StringBuilder sb, List<Property> properties) throws IllegalAccessException {
        if (properties == null) {
            return "";
        }
        //过滤筛选条件，value和cType为0的被过滤
        List<Property> filterproperty = new ArrayList<>();
        for (int i = 0; i < properties.size(); i++) {
            String compareSql = getCompareSql(properties.get(i).getCompareType());
            if (!StringUtils.isEmpty(compareSql) && !StringUtils.isEmpty(properties.get(i).getValue().toString())) {
                filterproperty.add(properties.get(i));
            }
        }
        for (int i = 0; i < filterproperty.size(); i++) {
            Property property = filterproperty.get(i);

//            if (i <= filterproperty.size() - 1) {
//                sb.append(" and ");
//            }
            getCompareSql(sb, property);

        }

        return sb.toString();
    }

    /**
     * 组装条件
     *
     * @param sb
     * @param property
     */
    public void getCompareSql(StringBuilder sb, Property property) throws IllegalAccessException {
        String compareSql = getCompareSql(property.getCompareType());
        if (StringUtils.isEmpty(property.getFieldName())) {
            return;
        }
        //like 比较只能是字符串比较
        if (!JavaClassEnum.STRING.getDesc().equalsIgnoreCase(property.getFieldType())&&CompareTypeEnum.LIKE.getDesc().equalsIgnoreCase(property.getCompareType()))
        {
            throw new  IllegalAccessException("非字符串不能用模糊查询");
        }
            //比较字段为字符串
        if (JavaClassEnum.STRING.getDesc().equalsIgnoreCase(property.getFieldType())) {
            sb.append(" and "+property.getFieldName()).append(" ")
                    .append(compareSql);
            if (CompareTypeEnum.LIKE.getDesc().equalsIgnoreCase(property.getCompareType())) {
                sb.append(" '%").append(property.getValue()).append("%' ");
            }else {
                sb.append(" '").append(property.getValue()).append("' ");
            }
            }
        //比较类型为日期
        if (JavaClassEnum.DATE.getDesc().equalsIgnoreCase(property.getFieldType())) {
            sb.append(" and "+property.getFieldName()).append(" ")
                    .append(compareSql);
            boolean b = property.getFieldType().matches("\\d{4}-\\d{2}-\\d{2}");
            if (b) {
                sb.append(" '").append(property.getValue()).append("' ");
            }
        }
        if (JavaClassEnum.INTEGER.getDesc().equalsIgnoreCase(property.getFieldType()) || JavaClassEnum.INT.getDesc().equals(property.getFieldType())) {
            sb.append(" and "+property.getFieldName()).append(" ")
                    .append(compareSql);
            sb.append(" ").append(property.getValue()).append(" ");
        }

//        if (property.getFieldType().equals("Date")) {
//            sb.append("to_date('")
//                    .append(property.getValue()).append("','yyyy-MM-dd'");
//        } else if (property.getFieldType().equals("String")) {
//            if (compareSql.equals(CompareTypeEnum.LIKE.getDesc())) {
//                sb.append("('%").append(property.getValue());
//
//            } else {
//                sb.append("'").append(property.getValue());
//            }
//        }

    }

    /**
     * 转换成数据库的比较符号
     *
     * @param compareType
     * @return
     */
    private String getCompareSql(String compareType) {
        String compareSql = "";
        if (compareType.equals(CompareTypeEnum.LESS.getDesc())) {
            compareSql = "<";
        } else if (compareType.equals(CompareTypeEnum.GREATER.getDesc())) {
            compareSql = ">";
        } else if (compareType.equals(CompareTypeEnum.EQUALS.getDesc())) {
            compareSql = "=";
        } else if (compareType.equals(CompareTypeEnum.LIKE.getDesc())) {
            compareSql = "like";
        }
        return compareSql;
    }

    /**
     * 拼接获取条数的sql语句
     * <p>
     *
     * @param sqlPrimary
     */
    protected String getCountSql(String sqlPrimary, Object pageParamater, PageParameter page) {
        String sqlUse = sqlPrimary.replaceAll("[\\s]+", " ");
        String upperString = sqlUse.toUpperCase();
        int order_by = upperString.lastIndexOf(" ORDER BY ");
        if (order_by > -1) {
            sqlUse = sqlUse.substring(0, order_by);
        }
        String[] paramsAndMethod = sqlUse.split("\\s");
        int count = 0;
        int index = 0;
        for (int i = 0; i < paramsAndMethod.length; i++) {
            String upper = paramsAndMethod[i].toUpperCase();
            if (upper.length() == 0) {
                continue;
            }
            if (upper.contains("SELECT")) {
                count++;
            } else if (upper.contains("FROM")) {
                count--;
            }
            if (count == 0) {
                index = i;
                break;
            }
        }
        StringBuilder return_sql = new StringBuilder("SELECT COUNT(1) AS cnt  from(");
        StringBuilder common_count = new StringBuilder();
        for (int j = index; j < paramsAndMethod.length; j++) {
            common_count.append(" ");
            common_count.append(paramsAndMethod[j]);
        }
        if (upperString.contains(" GROUP BY ")) {
            throw new RuntimeException("不支持group by 分页,请自  提供sql语句以 查询语句+_count结尾.");
        }
        String returnSql = return_sql.append(sqlPrimary + ") tb").toString();

        System.out.println("查询总数sql------------------------------:\n" + returnSql);
        return returnSql;
    }

    /**
     * 计算总条数
     * <p>
     *
     * @param countSql
     * @param connection
     * @return
     */
    protected int getTotalSize(Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql,
                               String countSql, Connection connection, Object parameter) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int totalSize = 0;
        try {
            ParameterHandler handler = configuration.newParameterHandler(mappedStatement, parameter, boundSql);
            stmt = connection.prepareStatement(countSql);
            handler.setParameters(stmt);
            rs = stmt.executeQuery();
            if (rs.next()) {
                totalSize = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
        }
        return totalSize;
    }

    /**
     * 寻找page对象
     * <p>
     *
     * @param parameter
     */
    @SuppressWarnings("rawtypes")
    protected PageParameter seekPage(Object parameter) {
        PageParameter page = null;
        if (parameter == null) {
            return null;
        }
        if (parameter instanceof PageParameter) {
            page = (PageParameter) parameter;
            System.out.println("总记录数：" + page.getPageSize());
        } else if (parameter instanceof Map) {
            Map map = (Map) parameter;
            for (Object arg : map.values()) {
                if (arg instanceof PageParameter) {
                    page = (PageParameter) arg;
                }
            }
        }
        return page;
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {

    }
}