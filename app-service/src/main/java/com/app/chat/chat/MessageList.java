package com.app.chat.chat;

import java.util.ArrayList;
import java.util.List;

/**
 * 信息集合 这个对象储存到Application中
 * @author Pan
 *
 */
public class MessageList {

	private List<Message> list=new ArrayList<Message>();
	
	public MessageList(){
		
	}
	
	/**
	 * 添加信息
	 * @param message
	 */
	public void add(Message message){
		list.add(message);
	}
	
	/**
	 * 寻找信息 通过接受的用户
	 * @param uid
	 * @return
	 */
	public List<Message> getByTo(String uid){
		List<Message> tmp=new ArrayList<Message>();
		System.out.println("list的大小："+list.size());
		for(Message message:list){
			if(message.getTo().equals(uid)){
				//添加到返回对象
				tmp.add(message);
			}
		}
		//然后移除信息 以免信息重复
		list.removeAll(tmp);
		return tmp;
	}
	
}
