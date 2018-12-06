package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import com.app.chat.admin.Admin;
import com.app.chat.admin.AdminListServler;
import com.app.chat.admin.AdminSimplify;

import javax.servlet.http.HttpServletRequest;

public class AdminListAction extends BaseController {

	private List<AdminSimplify> list;
	
	public List<AdminSimplify> getList() {
		return list;
	}
	/**
	 * 得到当前登陆的管理员
	 * @return
	 */
	public String execute(){
		
//		list=new ArrayList<AdminSimplify>();
//		AdminListServler aServler=new AdminListServler(HttpServletRequest.getRequest().getSession());
//		for (Admin admin : aServler.getList()) {
//
//			AdminSimplify as=new AdminSimplify();
//			as.setAddTime(admin.getAddTime());
//			as.setAdminName(admin.getAdminName());
//			as.setAdminPass(admin.getAdminPass());
//			as.setHead(admin.getHead());
//			as.setId(admin.getId());
//			as.setNickName(admin.getNickName());
//			list.add(as);
//		}
//
		return "";
	}
}
