package com.app.service;

import com.app.utils.GenerationMapperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * com.app.utils
 *
 * @param ${param}
 * @author zhujiamin
 * @date 2017/8/2
 */
@Service
public class GenerationClassServiceImpl {
//    @Autowired private GenerationMapperDao generationMapperDao;
//    // 数据库数据类型、java数据类型映射mapprivatestatic
//    public static final Map<String,String> dataTypeMapping =new HashMap<>();
//    // 初始化数据库类型与java类型转换
//    static {
//        dataTypeMapping.put("int", "int");
//        dataTypeMapping.put("tinyint", "int");
//        dataTypeMapping.put("smallint", "int");
//        dataTypeMapping.put("mediumint", "int");
//        dataTypeMapping.put("integer", "int");
//        dataTypeMapping.put("bigint", "long");
//        dataTypeMapping.put("bit", "boolean");
//        dataTypeMapping.put("real", "float");
//        dataTypeMapping.put("double", "double");
//        dataTypeMapping.put("float", "float");
//        dataTypeMapping.put("float", "float");
//        dataTypeMapping.put("decimal", "BigDecimal");
//        dataTypeMapping.put("numeric", "BigDecimal");
//        dataTypeMapping.put("char", "String");
//        dataTypeMapping.put("varchar", "String");
//        dataTypeMapping.put("date", "java.util.Date");
//        dataTypeMapping.put("time", "java.util.Time");
//        dataTypeMapping.put("year", "java.util.Date");
//        dataTypeMapping.put("timestamp", "java.util.Timestamp");
//        dataTypeMapping.put("datetime", "java.util.Date");
//        dataTypeMapping.put("tinyblob", "byte[]");
//        dataTypeMapping.put("blob", "byte[]");
//        dataTypeMapping.put("mediumblob", "byte[]");
//        dataTypeMapping.put("longblob", "byte[]");
//        dataTypeMapping.put("tinytext", "String");
//        dataTypeMapping.put("text", "String");
//        dataTypeMapping.put("mediumtext", "String");
//        dataTypeMapping.put("longtext", "String");
//        dataTypeMapping.put("binary", "byte[]");
//    }
//    /**
//     * @Description 生成java实体
//     * @author zhujiamin
//     * @date 2015年12月9日 上午10:11:17
//     * @param tableForm
//     * @return
//     */
//    @Override
//    public Map<String, Object> getJava(TableForm tableForm) {
//        List<Variable> tableInfo = generationMapperDao.getTableInfo(tableForm);
//        Map<String, Object> sqlMap = newHashMap < String, Object>();
//        String sqlString = createJava(tableInfo, tableForm);
//        sqlMap.put("list", sqlString);
//        return sqlMap;
//    }
//
//
//    /**
//     * @Description 创建java实体字符串
//     * @author zhangyd
//     * @date 2015年12月9日 上午10:11:22
//     * @param tableInfo
//     * @param
//     * @return
//     */
//    @Override
//    public String createJava(List<Variable> tableInfo, TableForm tableForm) {
//        StringBuilder sb = new StringBuilder();
//        // 包名
//        String packageString = createPackage(tableForm.getEntityPackage());
//        sb.append(packageString);//
//        String classStringclassString = createClass(tableForm.getSelectedTableNames());
//        sb.append(classString);// 变量String variablesString = createVariables(tableInfo);
//        sb.append(variablesString);// SetAndGetStringSetAndGetString= createSetAndGet(tableInfo);
//        sb.append(SetAndGetString);// 尾部String tailString = createTail();
//        sb.append(tailString);
//        return sb.toString();
//    }
//
//    /**
//     * @param packagePath
//     * @return
//     * @Description 创建包信息
//     * @author zhangyd
//     * @date 2015年12月9日 上午10:11:28
//     */
//    @Override
//    public String createPackage(String packagePath) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("package " + packagePath + ";<br><br>");
//        return sb.toString();
//    }
//
//    /**
//     * @Description 创建导入信息
//     * @author zhangyd
//     * @date 2015年12月9日 上午10:11:33
//     * @return
//     */
//    @Override
//    public String createImport() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("<br>");
//        return sb.toString();
//    }/**
//     * @Description 创建class名
//     * @author zhangyd
//     * @date 2015年12月9日 上午10:11:39
//     * @param tableName
//     * @return
//     */
//    @Override
//    public String createClass(String tableName) {
//        StringBuilder sb = new StringBuilder();
//        String[] temps = tableName.split("_");
//        tableName = temps[0];
//        for (int i = 1; i < temps.length; i++) {
//            tableName += StringUtil.initialtoUpper(temps[i]);
//        }
//        sb.append("public class " + StringUtil.initialtoUpper(tableName) + " {<br>");
//        return sb.toString();
//    }
///**
// * @Description 创建变量
// * @author zhangyd
// * @date 2015年12月9日 上午10:11:55
// * @param tableInfo
// * @return
// */
//
//@Override
//public String createVariables(List<Variable> tableInfo) {
//    StringBuilder sb = new StringBuilder();
//    for (Variable variable : tableInfo) {
//        if (StringUtil.isNotEmpty(variable.getComment())) {
//            sb.append(" //" + variable.getComment() + "<br>");
//        }
//        sb.append(" private " + dataTypeMapping.get(variable.getType()) + " " + variable.getPropertyNames() + ";<br>");
//    }
//    sb.append("<br>");
//    return sb.toString();
//}
///**
// * @Description 创建set、get方法
// * @author zhangyd
// * @date 2015年12月9日 上午10:12:02
// * @param tableInfo
// * @return
// */
//@Override
//public String createSetAndGet(List<Variable> tableInfo) {
//    StringBuilder sb = new StringBuilder();
//    for (Variable variable : tableInfo) {
//        sb.append(" public void set" + StringUtil.initialtoUpper(variable.getPropertyNames()) + "(" + dataTypeMapping.get(variable.getType()) + " " + variable.getPropertyNames() + "){<br>");
//        sb.append("     this." + variable.getPropertyNames() + " = " + variable.getPropertyNames() + ";<br>");
//        sb.append(" }<br><br>");
//
//        sb.append(" public " + dataTypeMapping.get(variable.getType()) + " get" + StringUtil.initialtoUpper(variable.getPropertyNames()) + "(){<br>");
//        sb.append("     return this." + variable.getPropertyNames() + ";<br>");
//        sb.append(" }<br><br>");
//    }
//    return sb.toString();
//}
//
//    @Override
//    public String createTail() {
//        return "}";
//    }
//}
}