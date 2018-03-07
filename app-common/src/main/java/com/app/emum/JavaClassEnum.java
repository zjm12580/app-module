package com.app.emum;

/**
 * com.app.emum
 *
 * @param ${param}
 * @author zhujiamin
 * @date 2017/7/31
 */
public enum JavaClassEnum {
    INTEGER("Integer"), STRING("String"), DATE("Date"), INT("int");
    private String desc;

    JavaClassEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
