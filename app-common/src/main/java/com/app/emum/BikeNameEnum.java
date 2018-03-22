package com.app.emum;

/**
 * Created by zhujiamin on 2018/3/21.
 */
public enum BikeNameEnum {

    BIKENAME1(0,"版本1测试车"),
    BIKENAME2(1,"版本2测试车"),
    BIKENAME3(2,"版本3测试车"),
    BIKENAME4(3,"版本4测试车"),
    BIKENAME5(4,"版本5测试车"),
    BIKENAME6(5,"版本6测试车"),
    BIKENAME7(6,"版本7测试车");

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

    BikeNameEnum(Integer value, String sexName ) {
        this.name=sexName;
        this.value=value;

    }

    public static String get(Integer value) {
        if (value!=null) {
            for (BikeNameEnum code : BikeNameEnum.values()) {
                if (value.equals(code.getValue())) {
                    return code.getName();
                }
            }
        }
        return null;
    }
}
