package com.app.chat.chat;

/**
 * 信息容器
 * @author Pan
 *
 */

public class Message {

	private String form; // 来自用户
	private String to; // 接受用户
	private String message; // 信息
	private String nickName;	//昵称
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getNickName() {
		return nickName;
	}
	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
