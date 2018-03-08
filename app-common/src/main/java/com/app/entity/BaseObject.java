package com.app.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by zhujiamin on 2018/2/25.
 */
public class BaseObject {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date mtime;
    private  Integer modifer;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ctime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Integer getModifer() {
        return modifer;
    }

    public void setModifer(Integer modifer) {
        this.modifer = modifer;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}
