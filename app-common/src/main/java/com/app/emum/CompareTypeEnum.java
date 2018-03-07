package com.app.emum;

/**
 * Created by zhujiamin on 2017/11/29.
 */
public enum CompareTypeEnum {

    EQUALS("equale"),GREATER("greater"),LESS("less"),LIKE("like");
    private String desc;
    CompareTypeEnum(String desc){
        this.desc=desc;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
