package com.app.service.entity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * com.zjm.controller
 *
 * @param ${param}
 * @author zhujiamin
 * @date 2017/7/3
 */

@Controller
@RequestMapping("/app")
public class MainController {
    @RequestMapping("/getRequest")
    @ResponseBody
    public String getRequest(HttpServletRequest request){
        System.out.println("朱佳敏");
        return "11234东东";
    }
}
