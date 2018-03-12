package com.app.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * controller返回实体
 * Created by zhujiamin on 2017/11/30.
 */

public class ResultMap<T> {

    private static final Boolean SUCCESS=Boolean.TRUE;

    private static final Boolean FAILED=Boolean.FALSE;

    private T data;

    /**
     * 返回成功
     * @param message
     * @return
     */
    public static Map success(String message) {
        Map<String, Object> res = new HashMap<>();
        res.put("status", SUCCESS);
        res.put("msg", message);
        return res;
    }

    /**
     * 返回失败
     * @param message
     * @return
     */
    public static Map failed(String message) {
        Map<String, Object> res = new HashMap<>();
        res.put("status", FAILED);
        res.put("msg", message);
        return res;
    }

    /**
     * 带参数返回
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Map setData(T data, String message) {
        Map<String, Object> res = new HashMap<>();
        res.put("status", SUCCESS);
        res.put("msg", message);
        res.put("data",data);
        return null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 返回成功
     * @return
     */
    public static Map success() {
        Map<String, Object> res = new HashMap<>();
        res.put("status", SUCCESS);
        return res;
    }
}
