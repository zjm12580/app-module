package com.app.emum;

/**
 * Created by zhujiamin on 2018/3/21.
 */
public enum  BikeStatusEnum {

    REPARED(0,"已修复"),
    UNREPAR(1,"未修复");

    Integer value;
    String name;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    BikeStatusEnum(Integer value, String sexName ) {
        this.name=sexName;
        this.value=value;

    }

    public static String get(Integer value) {
        if (value!=null) {
            for (BikeStatusEnum code : BikeStatusEnum.values()) {
                if (value.equals(code.getValue())) {
                    return code.getName();
                }
            }
        }
        return null;
    }
}
