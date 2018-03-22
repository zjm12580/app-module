package com.app.emum;

/**
 * com.app.utils
 *
 * @param ${param}
 * @author zhujiamin
 * @date 2017/7/26
 */
public enum CompareEnum {
    LIKE("like"),
    EQUAL("equal");
    private String desc;

    CompareEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
