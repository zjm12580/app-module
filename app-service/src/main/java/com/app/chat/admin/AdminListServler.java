package com.app.chat.admin;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * admin列表服务
 * @author Pan
 *
 */
public class AdminListServler {

	private AdminList list;
	
	public AdminListServler(HttpSession session){
		ServletContext context=session.getServletContext();
		list=(AdminList)context.getAttribute("adminlist");
		if(list==null){
			list=new AdminList();
			context.setAttribute("adminlist", list);
		}
	}
	/**
	 * add an admin to the list
	 * @param admin
	 */
	public void save(Admin admin){
		list.save(admin);
	}
	
	/**
	 * get all admin object
	 * @return arraylist
	 */
	public List<Admin> getList(){
		return list.getList();
	}
}
