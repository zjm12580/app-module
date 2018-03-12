package com.app.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static final SerializerFeature[] features = { SerializerFeature.SkipTransientField,
            SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteEnumUsingToString };

    public static String toJsonString(Object object) {
        return JSONObject.toJSONString(object, features);
    }

    public static String toJsonpString(Object obj, String callback) {
        return callback + "(" + toJsonString(obj) + ")";
    }

    public static Map parseObject(String json) {
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject jsonObject = JSONObject.parseObject(json);
        return jsonObject;
    }

    public static Object parseObjectOrArray(String json, Class<?> clazz) {
        Object o = JSON.parse(json);
        if ((o instanceof JSONArray)) o = JSONObject.parseArray(json, clazz);
        else {
            o = JSONObject.parseObject(json, clazz);
        }
        return o;
    }

    public static <T> T parseObject(String json, Class<T> clazz) {
        return JSONObject.parseObject(json, clazz);
    }

    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        return JSONObject.parseArray(json, clazz);
    }

    /**
     * 对JSON每一个KEY的VALUE文本中可能引起问题的特殊字符串做JS转义 双引号,单引号都转义
     * 
     * @param json
     * @return
     */
    public static String escapeValue(String json) {
        if (StringUtils.isBlank(json)) return json;

        json = json.replaceAll("\\\\", "\\\\\\\\");
        json = json.replaceAll("\"", "\\\\\"");
        json = json.replaceAll("'", "\\\\'");
        json = json.replaceAll("\b", "\\\\b");
        json = json.replaceAll("\n", "\\\\n");
        json = json.replaceAll("\t", "\\\\t");
        json = json.replaceAll("\f", "\\\\f");
        json = json.replaceAll("\r", "\\\\r");

        return json;
    }
}
