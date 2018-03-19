package com.app.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * User 实体类
 * Tue Nov 21 19:29:41 CST 2017 zjm
 */


public class User {
    private long id;
    private String userName;
    private String password;
    /**
     * 性别 1 男性  0 女性
     */
    private Integer sex;
    private String phone;
    private String departent;
    private Long ruleId;
    private String realName;
    private Date ctime;
    private Date mtime;
    private String sexName;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datemin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datemax;

    private Integer start;

    private Integer length;

    private Integer status;

    private String email;
    private String remark;
    private Integer departmentId;


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public Date getDatemin() {
        return datemin;
    }

    public void setDatemin(Date datemin) {
        this.datemin = datemin;
    }

    public Date getDatemax() {
        return datemax;
    }

    public void setDatemax(Date datemax) {
        this.datemax = datemax;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setDepartent(String departent) {
        this.departent = departent;
    }

    public String getDepartent() {
        return departent;
    }


    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Date getMtime() {
        return mtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

