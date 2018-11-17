package com.app.chat.admin;

import java.util.ArrayList;
import java.util.List;


/**
 * 存放当前已经登陆的管理员列表
 * @author Pan
 *
 */
public class AdminList {

	private List<Admin> list=new ArrayList<Admin>();
	public AdminList(){
		
	}
	
	/**
	 * 增加Admin对象
	 * @param admin
	 */
	public void save(Admin admin){
		if(list.indexOf(admin)==-1){
			list.add(admin);
		}
	}
	
	/**
	 * 得到所有Admin
	 * @return
	 */
	public List<Admin> getList(){
		return list;
	}
}
