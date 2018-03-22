package com.app.emum;

/**
 * Created by zhujiamin on 2018/3/21.
 */
public enum ExtentEnum {
    HANDLEBAR(0,"轻微"),
    BODY(1,"中等"),
    PEDAL(2,"严重");

    Integer value;
    String name;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getSexName() {
        return name;
    }

    public void setSexName(String sexName) {
        this.name = sexName;
    }

    ExtentEnum(Integer value, String sexName ) {
        this.name=sexName;
        this.value=value;

    }

    public static ExtentEnum get(Integer value) {
        if (value!=null) {
            for (ExtentEnum code : ExtentEnum.values()) {
                if (value.equals(code.getValue())) {
                    return code;
                }
            }
        }
        return null;
    }
}
