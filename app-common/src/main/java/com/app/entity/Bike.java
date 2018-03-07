package com.app.entity;

import com.sun.xml.internal.rngom.parse.host.Base;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 单车实体类
 * Created by zhujiamin on 2018/2/25.
 */
public class Bike  extends BaseObject{

    private  String name;

    private Integer type;

    private String remark;

    private String pictureUrl;

    private BigDecimal price;

    private BigDecimal rent;

    private Integer isdel;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datemin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  Date datemax;

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

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }
}
