package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.app.chat.chat.Message;
import com.app.chat.chat.MessageServer;
import com.app.entity.User;
import com.app.utils.ResultMap;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.web.bind.annotation.RequestMapping;

public class MessageAction extends BaseController {

	private String to;			//传送到指定的用户
	private String result;		//返回信息
	private String content;		//内容
	private String nickName;	//昵称
	private List<Message> list;	//消息集合
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Message> getList() {
		return list;
	}
	public String getResult() {
		return result;
	}
	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * 获取消息
	 */
	@RequestMapping("/chat/getMsg")
	public Object execute(){
		
		User user=(User)request.getSession().getAttribute("user");
		MessageServer msServer=new MessageServer(request.getSession(), user.getId()+"",user.getNickName());
		this.list=msServer.getMessage();
		
		return ResultMap.success();
	}

	/**
	 * 发送消息
	 * @return
	 */
	@RequestMapping("/chat/sendMsg")
	public Object send(){
		
		User user=(User)request.getSession().getAttribute("user");
//		if(user==null){
//			result="你还没有进行登录！";
//			return ResultMap.failed();
//		}
		MessageServer mServer=new MessageServer(request.getSession(),user.getId()+"",user.getNickName());
		mServer.getMessage();
		
		try {
			mServer.send(to, content);
			
			System.out.println("to:"+to);
			setNickName(user.getNickName());
			setResult("发送成功");
		} catch (Exception e) {
			setResult("发送失败！");
			result=e.getMessage();
		}		
		return ResultMap.success();
	}
}
