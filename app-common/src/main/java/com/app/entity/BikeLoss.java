package com.app.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * BikeLoss 实体类
 * Tue Mar 20 11:06:22 CST 2018 zjm
 */


public class BikeLoss {
    private Integer id;
    private String name;
    private Integer type;
    private String remark;
    private String bikeNumber;
    private String lossPosition;
    private Double expectCost;
    private Double actualCost;
    private Date ctime;
    private Date mtime;
    private Long modifer;
    private Integer isdel;
    private Integer lossSituation;
    private Double rent;
    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datemin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  Date datemax;


    private Integer start;

    private Integer length;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBikeNumber() {
        return bikeNumber;
    }

    public void setBikeNumber(String bikeNumber) {
        this.bikeNumber = bikeNumber;
    }

    public String getLossPosition() {
        return lossPosition;
    }

    public void setLossPosition(String lossPosition) {
        this.lossPosition = lossPosition;
    }

    public Double getExpectCost() {
        return expectCost;
    }

    public void setExpectCost(Double expectCost) {
        this.expectCost = expectCost;
    }

    public Double getActualCost() {
        return actualCost;
    }

    public void setActualCost(Double actualCost) {
        this.actualCost = actualCost;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Long getModifer() {
        return modifer;
    }

    public void setModifer(Long modifer) {
        this.modifer = modifer;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Integer getLossSituation() {
        return lossSituation;
    }

    public void setLossSituation(Integer lossSituation) {
        this.lossSituation = lossSituation;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }
}

