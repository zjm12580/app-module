package com.app.utils;

import com.app.entity.Visitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhujiamin on 2018/3/22.
 */
public class Visit {

    public static  Map<Integer, Visitor> visitorMap;

    public static int i = 0;


    static {
        visitorMap=new HashMap<>();
    }


}
