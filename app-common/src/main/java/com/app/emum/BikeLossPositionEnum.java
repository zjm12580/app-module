package com.app.emum;

import com.app.utils.SexEnum;

/**
 * Created by zhujiamin on 2018/3/21.
 */
public enum BikeLossPositionEnum {

    HANDLEBAR(0,"车把"),
    BODY(1,"车身"),
    PEDAL(2,"踏板"),
    WHEEL(3,"轮子");

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

    BikeLossPositionEnum(Integer value, String sexName ) {
        this.name=sexName;
        this.value=value;

    }

    public static String get(Integer value) {
        if (value!=null) {
            for (BikeLossPositionEnum code : BikeLossPositionEnum.values()) {
                if (value.equals(code.getValue())) {
                    return code.getName();
                }
            }
        }
        return null;
    }

}
