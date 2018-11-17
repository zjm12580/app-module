package com.app.entity;

import java.util.Date;

/**
    * areaManager 实体类
    * Mon Apr 23 23:42:22 CST 2018 zjm
    */ 


public class AreaManager{
	private Long id;
	private Long areaId;
	private String areaName;
	private Long bikeAmount;
	private Long studentAmount;
	private Long needBikeAmount;
	private Long manager;
	private Long lossAmount;
	/**
	*待回收金额
	*/	
	private Long needRecovered;
	private Long isdel;
	private Date ctime;
	private Long creater;

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

	public void setId(Long id){
	this.id=id;
	}

	public Long getId(){
		return id;
	}

	public void setAreaId(Long areaId){
	this.areaId=areaId;
	}

	public Long getAreaId(){
		return areaId;
	}

	public void setAreaName(String areaName){
	this.areaName=areaName;
	}

	public String getAreaName(){
		return areaName;
	}

	public void setBikeAmount(Long bikeAmount){
	this.bikeAmount=bikeAmount;
	}

	public Long getBikeAmount(){
		return bikeAmount;
	}

	public void setStudentAmount(Long studentAmount){
	this.studentAmount=studentAmount;
	}

	public Long getStudentAmount(){
		return studentAmount;
	}

	public void setNeedBikeAmount(Long needBikeAmount){
	this.needBikeAmount=needBikeAmount;
	}

	public Long getNeedBikeAmount(){
		return needBikeAmount;
	}

	public void setManager(Long manager){
	this.manager=manager;
	}

	public Long getManager(){
		return manager;
	}

	public void setLossAmount(Long lossAmount){
	this.lossAmount=lossAmount;
	}

	public Long getLossAmount(){
		return lossAmount;
	}

	public void setNeedRecovered(Long needRecovered){
	this.needRecovered=needRecovered;
	}

	public Long getNeedRecovered(){
		return needRecovered;
	}

	public Long getIsdel() {
		return isdel;
	}

	public void setIsdel(Long isdel) {
		this.isdel = isdel;
	}

	public void setCtime(Date ctime){
	this.ctime=ctime;
	}

	public Date getCtime(){
		return ctime;
	}

	public void setCreater(Long creater){
	this.creater=creater;
	}

	public Long getCreater(){
		return creater;
	}
}

