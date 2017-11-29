package com.app.entity;

import java.util.Date;

/**
    * User 实体类
    * Tue Nov 21 19:29:41 CST 2017 zjm
    */ 


public class User{
	private long id;
	private String userName;
	private String password;
	/**
	*性别 1 男性  0 女性
	*/	
	private byte sex;
	private String phone;
	private String departent;
	private long ruleId;
	private String realName;
	private Date ctime;
	private Date mtime;

	public void setId(long id){
	this.id=id;
	}

	public long getId(){
		return id;
	}

	public void setUserName(String userName){
	this.userName=userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setPassword(String password){
	this.password=password;
	}

	public String getPassword(){
		return password;
	}

	public void setSex(byte sex){
	this.sex=sex;
	}

	public byte getSex(){
		return sex;
	}

	public void setPhone(String phone){
	this.phone=phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setDepartent(String departent){
	this.departent=departent;
	}

	public String getDepartent(){
		return departent;
	}

	public void setRuleId(long ruleId){
	this.ruleId=ruleId;
	}

	public long getRuleId(){
		return ruleId;
	}

	public void setRealName(String realName){
	this.realName=realName;
	}

	public String getRealName(){
		return realName;
	}

	public void setCtime(Date ctime){
	this.ctime=ctime;
	}

	public Date getCtime(){
		return ctime;
	}

	public void setMtime(Date mtime){
	this.mtime=mtime;
	}

	public Date getMtime(){
		return mtime;
	}
}

