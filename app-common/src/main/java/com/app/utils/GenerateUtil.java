package com.app.utils;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 生成的文件需要手动git add到本地库中 否则将不会提交
 */
public class GenerateUtil {

    private String       directoryPath    = "D:/generateFiles";

    /**
     * 模块的项目名
     */
    private String       daoMoudleName    = "";
    private String       commonMoldleName = "";
    private String       packagePath      = "";

    // private String entityOutPath = "com.ruhnn.wms.testGenerate";//指定实体生成所在包的路径
    // private String xmlOutPath = "com.ruhnn.wms.testGenerate";//xml项目路径
    // private String mapperOutPath = "com.ruhnn.wms.testGenerate";//mapper项目路径

    private String       entityOutPath    = "";                // 指定实体生成所在包的路径
    private String       xmlOutPath       = "";                // xml项目路径
    private String       mapperOutPath    = "";                // mapper项目路径
    private String       colStr           = "";

    private String       authorName       = "zjm";             // 作者名字
    private String       tablename        = "";                // 表名
    private String       aliasName        = "baiqishi";        // 产生实体类别名

    // private final String rootBasePath="\\src\\main\\java\\";
    private final String rootBasePath     = "";

    // private final String rootBasePath="\\src\\test\\main\\java\\";

    private List<String> colnames;                             // 列名数组
    private List<String> colTypes;                             // 列名类型数组
    private List<String> colRemark;
    private List<String> propertyNames;

    private int[]        colSizes;                             // 列名大小数s组
    private boolean      f_util           = false;             // 是否需要导入包java.util.*
    private boolean      f_sql            = false;             // 是否需要导入包java.sql.*

    // 其他数据库不需要这个方法 oracle和db2需要
    private String getSchema(Connection conn) throws Exception{
        String schema;
        schema = conn.getMetaData().getUserName();
        if ((schema == null) || (schema.length() == 0)) {
            throw new Exception("ORACLE数据库模式不允许为空");
        }
        return schema.toUpperCase().toString();

    }

    // 获取连接
    private Connection getConnections(String driver,String url,String user,String pwd) throws Exception{
        Connection conn = null;
        try {
            Properties props = new Properties();
            props.put("remarksReporting", "true");
            props.put("user", user);
            props.put("password", pwd);
            Class.forName(driver);
            conn = DriverManager.getConnection(url, props);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return conn;
    }

    /*
     * 构造函数
     */
    public GenerateUtil(String driver, String url, String user, String pwd, String table, String packagePath, String aliasName) throws Exception {
        this.packagePath = packagePath;
        this.aliasName = aliasName;
        List result = new ArrayList();
        // File directory = new File("");
        // directoryPath=directory.getAbsolutePath()+"/";
        Connection conn = null;
        DatabaseMetaData dbmd = null;
        try {
            conn = getConnections(driver, url, user, pwd);
            dbmd = conn.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, "%", table, new String[] { "TABLE" });

            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                this.tablename = tableName;
                ResultSet rs = conn.getMetaData().getColumns(null, getSchema(conn), tableName, "%");
                int i = 0;
                if (tableName.equals(table)) {
                    colnames = new ArrayList<>();
                    colTypes = new ArrayList<>();
                    colRemark = new ArrayList<>();
                    while (rs.next()) {
                        colnames.add(rs.getString("COLUMN_NAME"));
                        String type = rs.getString("TYPE_NAME");
                        if (type.equalsIgnoreCase("int")) {
                            type = "BIGINT";
                        }
                        if (type.equalsIgnoreCase("datetime")) {
                            type = "TIMESTAMP";
                        }
                        colTypes.add(type);
                        colRemark.add(rs.getString("REMARKS"));
                        if (colTypes.get(i).equalsIgnoreCase("datetime") || colTypes.get(i).equalsIgnoreCase("Date")) {
                            f_util = true;
                        }
                        if (colTypes.get(i).equalsIgnoreCase("image") || colTypes.get(i).equalsIgnoreCase("text")) {
                            f_sql = true;
                        }
                        i++;
                    }
                }

            }
            convertColumToName();
            // 生成实体类
            generateEntity();
            // 生成xml
            generateXML();
            // 生成mapper
            generateMapper();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

        }
    }

    /**
     * 生成XMl
     */
    private void generateXML(){
        File directory = new File("");
        String outputPath = directoryPath + daoMoudleName + rootBasePath + this.entityOutPath.replace(".", "\\") + "/" + tablename + ".xml";
        System.out.println("生成xml路径：-------" + outputPath);
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
        sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n");
        sb.append("<mapper namespace=\"" + mapperOutPath + "." + aliasName + "Mapper\">");
        sb.append("\r\n");
        sb.append("\t<resultMap id=\"baseResultMap\" type=\"" + packagePath + "." + aliasName + "\">\r\n");
        for ( int i = 0 ; i < colnames.size() ; i++ ) {
            sb.append("\t\t<result property=\"" + propertyNames.get(i) + "\"");
            sb.append(" column=\"" + colnames.get(i) + "\"");
            sb.append(" jdbcType=\"" + "" + colTypes.get(i) + "\"/>\r\n");
        }
        sb.append("\t</resultMap>");

        sb.append("\r\n");
        sb.append("<sql id=\"baseSql\">\r\n");
        sb.append("\t");
        for ( int i = 0 ; i < colnames.size() ; i++ ) {
            sb.append(colnames.get(i));
            colStr += colnames.get(i);
            if (i != colnames.size() - 1) {
                sb.append(",");
                colStr += ",";
            }
            else {
                sb.append("\r\n");
            }
        }
        sb.append("</sql>\r\n");

        // 生成insert语句
        sb.append("\r\n");
        // <insert id="insertSauron" parameterType="com.chuangjin.p2p.risk.manager.remoting.sauron.dto.Sauron">
        sb.append("<insert id=\"insert\" parameterType=\"" + rootBasePath + ".").append(aliasName).append("\">");

        sb.append("\r\n").append("\t");
        sb.append("INSERT INTO ").append(tablename).append("\r\n\t   ").append("(");
        for ( int i = 0 ; i < colnames.size() ; i++ ) {
            sb.append(colnames.get(i));
            if (i != colnames.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        sb.append("\r\n\t");
        sb.append("values (");
        sb.append("\r\n\t\t");
        for ( int i = 0 ; i < propertyNames.size() ; i++ ) {
            sb.append("#{").append(propertyNames.get(i)).append("}");
            if (i != propertyNames.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        sb.append("\r\n");

        sb.append("</insert>");
        sb.append("\r\n");
        // ----------------insert 语句结束

        // 查询
        sb.append("\r\n");
        sb.append("<select id=\"select\" ").append("parameterType=\"" + packagePath + ".").append(aliasName).append("\"");
        sb.append(" resultMap=\"baslResltMap\"").append(">");
        sb.append("\r\n\t");
        sb.append("SELECT").append("\r\n\t\t").append(colStr).append("\r\n");
        sb.append("\tFROM").append("\r\n").append("\t\t").append(tablename);
        sb.append("\r\n");
        sb.append("</select>");
        sb.append("\r\n");
        // ------------查询
        sb.append("</mapper>");

        FileWriter fw = null;
        try {
            fw = new FileWriter(outputPath);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(sb.toString());
            pw.flush();
            pw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void generateMapper(){}

    /**
     * 对象属性转换为字段 例如：userName to user_name
     *
     * @param property
     *            字段名
     * @return
     */
    public static String propertyToField(String property){
        if (null == property) {
            return "";
        }
        char[] chars = property.toCharArray();
        StringBuffer sb = new StringBuffer();
        for ( char c : chars ) {
            if (CharUtils.isAsciiAlphaUpper(c)) {
                sb.append("_" + StringUtils.lowerCase(CharUtils.toString(c)));
            }
            else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 字段转换成对象属性 例如：user_name to userName
     *
     * @param field
     * @return
     */
    public String fieldToProperty(String field){
        if (null == field) {
            return "";
        }
        char[] chars = field.toCharArray();
        StringBuffer sb = new StringBuffer();
        for ( int i = 0 ; i < chars.length ; i++ ) {
            char c = chars[i];
            if (c == '_') {
                int j = i + 1;
                if (j < chars.length) {
                    sb.append(StringUtils.upperCase(CharUtils.toString(chars[j])));
                    i++;
                }
            }
            else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public void convertColumToName(){
        propertyNames = new ArrayList<>();
        for ( int i = 0 ; i < colnames.size() ; i++ ) {
            propertyNames.add(fieldToProperty(colnames.get(i)));
        }
    }

    /**
     * 生成实体类
     */
    private void generateEntity(){
        String content = parse(colnames, colTypes, colSizes);
        try {
            File directory = new File("");
            // System.out.println("绝对路径："+directory.getAbsolutePath());
            // System.out.println("相对路径："+directory.getCanonicalPath());
            String path = this.getClass().getResource("").getPath();

            // System.out.println(path);
            // System.out.println("src/?/" + path.substring(path.lastIndexOf("/com/", path.length())));
            // String outputPath = directory.getAbsolutePath()+ "/src/"+path.substring(path.lastIndexOf("/com/", path.length()), path.length()) + initcap(tablename) + ".java";
            String outputPath = directoryPath + commonMoldleName + rootBasePath + this.entityOutPath.replace(".", "\\") + "/" + aliasName + ".java";
            System.out.println("生成实体类路径:-----" + outputPath);

            FileWriter fw = new FileWriter(outputPath);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            pw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能：生成实体类主体代码
     *
     * @param colnames
     * @param colTypes
     * @param colSizes
     * @return
     */
    private String parse(List<String> colnames,List<String> colTypes,int[] colSizes){
        StringBuffer sb = new StringBuffer();
        sb.append("package " + this.packagePath + ";\r\n");
        // 判断是否导入工具包
        if (f_util) {
            sb.append("import java.util.Date;\r\n");
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n");
        }
        sb.append("\r\n");
        // 注释部分
        sb.append("   /**\r\n");
        sb.append("    * " + aliasName + " 实体类\r\n");
        sb.append("    * " + new Date() + " " + this.authorName + "\r\n");
        sb.append("    */ \r\n");
        // 实体部分
        sb.append("\r\n\r\npublic class " + aliasName + "{\r\n");
        processAllAttrs(sb);// 属性
        processAllMethod(sb);// get set方法
        sb.append("}\r\n");

        // System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * 功能：生成所有属性
     *
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb){

        for ( int i = 0 ; i < colnames.size() ; i++ ) {
            if (colRemark.get(i) != null && !(colRemark.get(i).equals(""))) {
                sb.append("\t/**\r");
                sb.append("\t*\b").append(colRemark.get(i)).append("\n");
                sb.append("\t*/\t\n");
            }
            sb.append("\tprivate " + sqlType2JavaType(colTypes.get(i)) + " " + fieldToProperty(colnames.get(i)) + ";\r\n");
        }

    }

    /**
     * 功能：生成所有方法
     *
     * @param sb
     */
    private void processAllMethod(StringBuffer sb){

        for ( int i = 0 ; i < colnames.size() ; i++ ) {
            sb.append("\r\n");
            sb.append("\tpublic void set" + initcap(fieldToProperty(colnames.get(i))) + "(" + sqlType2JavaType(colTypes.get(i)) + " " + fieldToProperty(colnames.get(i)) + "){\r\n");
            sb.append("\tthis." + fieldToProperty(colnames.get(i)) + "=" + fieldToProperty(colnames.get(i)) + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\r\n");
            sb.append("\tpublic " + sqlType2JavaType(colTypes.get(i)) + " get" + initcap(fieldToProperty(colnames.get(i))) + "(){\r\n");
            sb.append("\t\treturn " + fieldToProperty(colnames.get(i)) + ";\r\n");
            sb.append("\t}\r\n");
        }

    }

    /**
     * 功能：将输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private String initcap(String str){

        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }

        return new String(ch);
    }

    /**
     * 功能：获得列的数据类型
     *
     * @param sqlType
     * @return
     */
    private String sqlType2JavaType(String sqlType){

        if (sqlType.equalsIgnoreCase("bit")) {
            return "boolean";
        }
        else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "byte";
        }
        else if (sqlType.equalsIgnoreCase("smallint")) {
            return "short";
        }
        else if (sqlType.equalsIgnoreCase("int")) {
            return "int";
        }
        else if (sqlType.equalsIgnoreCase("bigint")) {
            return "long";
        }
        else if (sqlType.equalsIgnoreCase("float")) {
            return "float";
        }
        else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric") || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "double";
        }
        else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        }
        else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("Date") || sqlType.equalsIgnoreCase("TIMESTAMP")) {
            return "Date";
        }
        else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        }

        return null;
    }

    /**
     * 出口 TODO
     *
     * @param args
     */
    public static void main(String[] args){

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/app";
        String user = "root";
        String pwd = "123456";
        String table = "area_manager";
        String packagePath = "app.entity.visitor";
        String aliasName = "areaManager";
        try {
            new GenerateUtil(driver,url,user,pwd,table,packagePath,aliasName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // String table="risk_result_sauron";
        // Class<?> classType= SauronBindingPhones.class;
        // StringBuffer sql=new StringBuffer();
        // sql.append("INSERT INTO "+table+" (");
        // for (Field field:classType.getDeclaredFields()){
        // sql.append(field.getName()+",");
        // }
        // sql.append(") values (");
        // for (Field field:classType.getDeclaredFields()){
        // //System.out.println();
        // sql.append("#{"+field.getName()+"},");
        //
        // }
        // sql.append(")");
        // System.out.println(sql.toString());

    }

}