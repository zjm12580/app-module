package com.app.emum;

/**
 * Created by zhujiamin on 2018/3/21.
 */
public enum  BikeTypeEnum {


    BIKETYPE1(0,"类型1"),
    BIKETYPE2(1,"类型2"),
    BIKETYPE3(2,"类型3"),
    BIKETYPE4(3,"类型4"),
    BIKETYPE5(4,"类型5"),
    BIKETYPE6(5,"类型6"),
    BIKETYPE7(6,"类型7");

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

    BikeTypeEnum(Integer value, String sexName ) {
        this.name=sexName;
        this.value=value;

    }

    public static BikeTypeEnum get(Integer value) {
        if (value!=null) {
            for (BikeTypeEnum code : BikeTypeEnum.values()) {
                if (value.equals(code.getValue())) {
                    return code;
                }
            }
        }
        return null;
    }
}
