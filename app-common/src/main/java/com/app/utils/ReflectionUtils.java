package com.app.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 反射工具类
 *
 * @author huqitao
 */
public class ReflectionUtils extends org.springframework.util.ReflectionUtils {


    /**
     * 获取对象的所有属性
     *
     * @param o
     * @return
     */
    public static List<Field> getObjFields(Object o) {
        List<Field> fields = new LinkedList<>();
        for (Class<? extends Object> clazz = o.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {

                for (Field f : clazz.getDeclaredFields()) {
                    fields.add(f);
                }
            } catch (Exception localException) {
            }

        }

        return fields;
    }

    /**
     * 获取对象obj的所有方法
     *
     * @param object
     * @return
     */
    public static List<Method> getObjMethods(Object object) {

        List<Method> methods = new LinkedList<>();
        for (Class<? extends Object> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {


                for (Method method : clazz.getDeclaredMethods()) {
                    methods.add(method);
                }
            } catch (Exception localException) {
                clazz = clazz.getSuperclass();
            }
        }
        return methods;
    }

    public static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {
        for (Class<? extends Object> clazz = object.getClass(); clazz != Object.class; ) {
            try {
                return clazz.getDeclaredMethod(methodName, parameterTypes);
            } catch (Exception localException) {
                clazz = clazz.getSuperclass();
            }

        }

        return null;
    }

    public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters) {
        Method method = getDeclaredMethod(object, methodName, parameterTypes);

        method.setAccessible(true);
        try {
            if (method != null) {
                return method.invoke(object, parameters);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<String> getFields(Object o) {
        List<String> fieldNames = new ArrayList<String>();
        for (Class<? extends Object> clazz = o.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field[] fields = clazz.getDeclaredFields();
                for (Field f : fields) {
                    fieldNames.add(f.getName());
                }
            } catch (Exception localException) {
            }

        }

        return fieldNames;
    }

    public static List<String> getFields(Object o, String[] filter) {
        List<String> fieldNames = new ArrayList<String>();
        for (Class<?> clazz = o.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field[] fields = clazz.getDeclaredFields();
                for (Field f : fields) {
                    if ((filter != null) && (!StringUtilsNew.stringListContain(filter, f.getName()).booleanValue())) {
                        fieldNames.add(f.getName());
                    }
                }

            } catch (Exception localException) {
            }

        }

        return fieldNames;
    }

    public static Field getDeclaredField(Object object, String fieldName) {
        Class<?> clazz = object.getClass();

        while (clazz != Object.class) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (Exception localException) {
                clazz = clazz.getSuperclass();
            }

        }

        return null;
    }

    public static void setFieldValue(Object object, String fieldName, Object value) {
        Field field = getDeclaredField(object, fieldName);

        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Object getFieldValue(Object object, String fieldName) {
        Field field = getDeclaredField(object, fieldName);

        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
