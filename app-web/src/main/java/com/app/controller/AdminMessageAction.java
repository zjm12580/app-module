package com.app.controller;

import java.util.List;

import com.app.chat.admin.Admin;
import com.app.chat.chat.Message;
import com.app.chat.chat.MessageServer;
import com.app.utils.ResultMap;
import org.springframework.web.bind.annotation.RequestMapping;

public class AdminMessageAction extends BaseController {

	private String to;		//接受用户
	private String result;	//返回消息
	private String content;	//消息内容
	private String nickName;	//昵称
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setContent(String content) {
		this.content = content;
	}
	private List<Message> list;
	
	public List<Message> getList() {
		return list;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	public String getResult() {
		return result;
	}
	public void setTo(String to) {
		this.to = to;
	}


	public String execute(){
//		Admin admin=(Admin)ServletActionContext.getRequest().getSession().getAttribute("admin");
//		if(admin==null){
//			setResult("你还没有登录！");
//			return ResultMap.success();
//		}
//		MessageServer msServer=new MessageServer(ServletActionContext.getRequest().getSession(),admin.getId()+"",admin.getNickName());
//		list=msServer.getMessage();
//		return SUCCESS;
		return "";
	}

	/**
	 * 发送消息
	 * @return
	 */
	@RequestMapping("/chat/adminSendMsg")

	public Object send(Long userId,String userName){
//		Admin admin=(Admin)ServletActionContext.getRequest().getSession().getAttribute("admin");
//		if(admin==null){
//			setResult("没有登录");
//			return SUCCESS;
//		}
		
		MessageServer msServer=new MessageServer(request.getSession(),userId.toString(),userName );
		try {
			msServer.send(to, content);
			setResult("发送成功！");
			setNickName(userName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result=e.getMessage();
		}
		return ResultMap.success();
	}

	
}
