package com.app.entity;

/**
 * com.app.entity
 *
 * @author zhujiamin
 * @date 2017/7/24
 */
public class Property {

    private String name;

    private String type;

    private String value;

    private String compareType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCompareType() {
        return compareType;
    }

    public void setCompareType(String compareType) {
        this.compareType = compareType;
    }
}
