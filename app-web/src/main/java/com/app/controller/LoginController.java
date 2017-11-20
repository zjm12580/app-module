package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhujiamin on 2017/11/2.
 */
@Controller
@RequestMapping("/app")
public class LoginController extends BaseController {


    /**
     * 执行登陆
     *
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("/index")
    public String index(String userName, String password) {
        

        return "/index";
    }

    /**
     * 登陆页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {

        return "login";
    }
}
