package com.app.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtilsNew extends org.apache.commons.lang.StringUtils {

    public static final String CHARSET_ENCODING = "UTF-8";

    // public static void main(String[] args){
    // String p = "${title}-${name}";
    // Map<String, Object> map = new HashMap<String, Object>();
    // map.put("title", "aaaaaa");
    // map.put("name", "hqt");
    // System.out.println(replaceKeywords(p, map));
    // }
    /**
     * 用于解析${name}这种表达式
     * 
     * @param p
     * @return
     */
    public static List<String> getKeywords(String p){
        String reg = "(?<=(?<!\\\\)\\$\\{)(.*?)(?=(?<!\\\\)\\})";
        Matcher matcher = Pattern.compile(reg).matcher(p);
        List<String> list = new ArrayList<String>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

    public static String replaceKeywords(String p,Map<String, Object> params){
        List<String> keywords = getKeywords(p);
        for ( String keyword : keywords ) {
            Object o = params.get(keyword);
            p = p.replace("${" + keyword + "}", o == null ? "" : o.toString());
        }
        return p;
    }

    public static String replaceKeywordsStringMap(String p,Map<String, String> params){
        List<String> keywords = getKeywords(p);
        for ( String keyword : keywords ) {
            String o = params.get(keyword);
            p = p.replace("${" + keyword + "}", o == null ? "" : o);
        }
        return p;
    }
    public static String trueFalse(Boolean b){
        if(b == null) return null;
       if (b !=null && b==true )return "是";
        return "否";
    }

    public static boolean isChineseString(String string){
        if (isBlank(string))
            return false;

        String patternRange = "[\\u4E00-\\u9FA5]+";
        Pattern pattern = Pattern.compile(patternRange);
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static String nullToString(String string){
        return isBlank(string) ? "" : string;
    }

    public static String trim0(String num){
        int begin = 0;
        int end = num.length() - 1;

        while (num.charAt(end) == '0') {
            end--;
        }
        if (num.charAt(end) == '.')
            end--;

        return begin <= end ? num.substring(begin, end + 1) : "0";
    }

    public static String trimStr(String str){
        return trimToEmpty(str).replaceAll("[\\r\\n]", "");
    }

    public static Boolean stringListContain(String[] list,String value){
        Boolean c = Boolean.valueOf(false);
        String[] arrayOfString = list;
        int j = list.length;
        for ( int i = 0 ; i < j ; i++ ) {
            String s = arrayOfString[i];
            if (s.equals(value)) {
                c = Boolean.valueOf(true);
                break;
            }
        }
        return c;
    }

    /**
     * 提取字符串中所有的用&lt;&gt;描述的表达式，像&lt;0:id&gt;
     * 
     * @param description
     * @return
     */
    public static List<String> analysisExpression(String description){
        Pattern p = Pattern.compile("(<[^>]*>)");
        Matcher m = p.matcher(description);
        List<String> result = new ArrayList<String>();
        while (m.find()) {
            result.add(m.group());
        }
        return result;
    }

    /**
     * 解析字符串表达式 解析像&lt;0:id&gt;等表达式
     * 
     * @param description
     * @param args
     * @return
     */
    public static String analysisDescription(String description,Object[] args){
        Pattern p = Pattern.compile("(<[^>]*>)");
        Matcher m = p.matcher(description);
        List<String> result = new ArrayList<String>();
        while (m.find()) {
            result.add(m.group());
        }
        for ( String param1 : result ) {
            String pm = param1;
            pm = pm.replace("<", "");
            pm = pm.replace(">", "");
            String[] params = pm.split(":");
            if (params.length == 1) {
                Integer index = Integer.valueOf(Integer.parseInt(params[0]));
                Object obj = args[index.intValue()];
                description = description.replace(param1, obj == null ? "" : obj.toString());
            }
            else {
                Integer index = Integer.valueOf(Integer.parseInt(params[0]));
                String field = params[1];
                Object o = args[index.intValue()];
                if (o == null) {
                    description = description.replace(param1, "");
                }
                else {
                    Object fieldValue = ReflectionUtils.getFieldValue(o, field);
                    String value = (fieldValue == null ? "" : fieldValue.toString());
                    description = description.replace(param1, value);
                }
            }
        }
        return description;
    }

    public static String analysisDescription(String description,Object[] args,Object result){
        Pattern p = Pattern.compile("(<[^>]*>)");
        Matcher m = p.matcher(description);
        List<String> res = new ArrayList<String>();
        while (m.find()) {
            res.add(m.group());
        }
        for ( String param1 : res ) {
            String pm = param1;
            pm = pm.replace("<", "");
            pm = pm.replace(">", "");
            String[] params = pm.split(":");
            if (!params[0].equals("result")) {
                if (params.length == 1) {
                    Integer index = Integer.valueOf(Integer.parseInt(params[0]));
                    Object obj = args[index.intValue()];
                    description = description.replace(param1, obj == null ? "" : obj.toString());
                }
                else {
                    Integer index = Integer.valueOf(Integer.parseInt(params[0]));
                    String field = params[1];
                    Object o = args[index.intValue()];
                    if (o == null) {
                        description = description.replace(param1, "");
                    }
                    else {
                        Object fieldValue = ReflectionUtils.getFieldValue(o, field);
                        String value = (fieldValue == null ? "" : fieldValue.toString());
                        description = description.replace(param1, value);
                    }
                }
            }
            else if (params.length == 1) {

                description = description.replace(param1, result == null ? "" : result.toString());
            }
            else {
                String field = params[1];
                Object fieldValue = ReflectionUtils.getFieldValue(result, field);
                String value = fieldValue == null ? "" : fieldValue.toString();
                description = description.replace(param1, value);
            }
        }

        return description;
    }

    public static String patternStr(String str){
        Pattern pat = Pattern.compile("\t|\r|\n");
        Matcher mat = pat.matcher(str);
        return mat.replaceAll("");
    }

    /**
     * 正则替换内容为空
     */
    public static String patternStr(String str,String reg){
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        return matcher.replaceAll("").trim();
    }

    public static String getStackTraceAsString(Throwable e){
        StringWriter stringWriter = null;
        PrintWriter printWriter = null;
        StringBuffer error = null;
        try {
            stringWriter = new StringWriter();

            printWriter = new PrintWriter(stringWriter);

            e.printStackTrace(printWriter);

            error = stringWriter.getBuffer();
        }
        catch (Throwable localThrowable) {
            try {
                if (printWriter != null) {
                    printWriter.flush();
                    printWriter.close();
                }
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            try {
                if (stringWriter != null) {
                    stringWriter.flush();
                    stringWriter.close();
                }
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        finally {
            try {
                if (printWriter != null) {
                    printWriter.flush();
                    printWriter.close();
                }
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            try {
                if (stringWriter != null) {
                    stringWriter.flush();
                    stringWriter.close();
                }
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        return error.toString();
    }

    public static int getAsc(String st){
        byte[] gc = st.getBytes();
        int ascNum = gc[0] - 55;
        return ascNum;
    }

    public static String trimLeft(String str){
        if (str == null || str.equals("")) {
            return str;
        }
        else {
            return str.replaceAll("^[　 ]+", "");
        }
    }

    /* 去右空格 */
    public static String trimRight(String str){
        if (str == null || str.equals("")) {
            return str;
        }
        else {
            return str.replaceAll("[　 ]+$", "");
        }
    }

    /**
     * 手机号脱敏
     * 
     * @param telephone
     * @return
     */
    public static String telephoneDesensitization(String telephone){
        return telephone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 身份证脱敏
     * 
     * @param idcard
     * @return
     */
    public static String idcardDesensitization(String idcard){
        if (isEmpty(idcard)) {
            return null;
        }
        return idcard.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1*****$2");
    }

    /**
     * 银行卡脱敏
     * @param cardName
     * @return
     */
    public static String cardNumberDesensitization(String cardName){

        if(StringUtils.isNotBlank(cardName)){
            cardName= cardName.trim();
            //长度为1，不变
            if(cardName.length() > 4 ) {
                cardName =  "***************"+cardName.substring(cardName.length()-4,cardName.length());
            }
        }
        return cardName;
    }



    /**
     * 姓名脱敏
     * @param name
     * @return
     */
    public static String nameDesensitization(String name){
        if(StringUtils.isNotBlank(name)){
            name= name.trim();
            //长度为1，不变
            if(name.length() == 1){
                //长度为2，带一个*
            }else if (name.length()==2){
                name= name.substring(0,1)+"*";
                //长度为1，带一个**
            }else if (name.length() >2){
                name= name.substring(0,1)+"**";
            }
        }
        return name;
    }

    /**
     * 姓名脱敏(保留一位*)
     * @param name
     * @return
     */
    public static String nameDesensitization2(String name) {
        if (StringUtils.isNotBlank(name)) {
            name = name.trim();
            //长度大于等于2,带一个*
            if (name.length() >= 2) {
                name = name.substring(0, 1) + "*";
            }
        }
        return name;
    }

}
