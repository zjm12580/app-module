package com.app.utils;

/**
 * Created by zhujiamin on 2018/3/14.
 */
public enum SexEnum {

    MAN(0,"男性"),
    FEMALE(1,"女性");

    Integer value;
    String sexName;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    SexEnum(Integer value, String sexName ) {
        this.sexName=sexName;
        this.value=value;

    }

    public static String get(Integer value) {
        if (value!=null) {
            for (SexEnum code : SexEnum.values()) {
                if (value.equals(code.getValue())) {
                    return code.getSexName();
                }
            }
        }
        return null;
    }

}


