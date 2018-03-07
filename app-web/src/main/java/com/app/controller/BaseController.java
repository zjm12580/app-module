/**
 * Project Name:directly
 * File Name:BaseController.java
 * Package Name:com.haicang.directly.controller
 * Date:2016�?11�?15日下�?1:34:46
 * Copyright (c) 2016, hcmarketing@highstore.cn All Rights Reserved.
 *
 */

package com.app.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseController {

	protected HttpServletRequest request;
	protected HttpServletResponse response;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

}
