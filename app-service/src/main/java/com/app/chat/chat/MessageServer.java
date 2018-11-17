package com.app.chat.chat;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class MessageServer {

	private MessageList mList;
	private ServletContext context;
	private String uid;
	private String nickname;
	public MessageServer(HttpSession session,String uid,String nickname){
		
		this.context=session.getServletContext();
		
		Object object=context.getAttribute("mlist");
		if(object==null){
			mList=new MessageList();
			context.setAttribute("mlist", mList);
		}else {
			this.mList=(MessageList)object;
		}
		this.uid=uid;
		this.nickname=nickname;
	}
	/**
	 * 发送数据
	 * @throws Exception 
	 */
	public void send(String to,String content) throws Exception{
		
		if(uid==null){
			throw new Exception("用户名id无效");
		}
		
		Message message=new Message();
		message.setForm(uid);
		message.setTo(to);
		message.setMessage(content);
		message.setNickName(this.nickname);
		mList.add(message);
		
	}
	
	/**
	 * 接受信息
	 * @return
	 */
	public List<Message> getMessage(){
		//返回当前用户的信息
		System.out.println("uid是："+uid);
		return mList.getByTo(uid);
	}
	
}
