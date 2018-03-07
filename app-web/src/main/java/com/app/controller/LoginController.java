package com.app.controller;

import com.app.entity.PageParameter;
import com.app.utils.ResultMap;
import com.app.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by zhujiamin on 2017/11/2.
 */
@Controller
public class LoginController extends BaseController {


    /**
     * 执行登陆
     *
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("/app/index")
    public String index(String userName, String password) {

        return "/index";
    }

    /**
     * 登陆页面
     *
     * @return
     */
    @RequestMapping("/app/login")
    public String login() {
        return "login";
    }


    /**
     * 登陆
     * @param userName
     * @param passwords
     * @param indentifyCode
     * @return
     */
    @RequestMapping(value = "/app/toLogin",method = RequestMethod.POST)
    @ResponseBody
    public Map toLogin(String userName, String passwords, String indentifyCode) {
        PageParameter page =new PageParameter(request);
        if (StringUtils.isEmpty(userName)||StringUtils.isEmpty(passwords)){
            return ResultMap.success("账号密码都不能为空");
        }
        //登陆逻辑


        return ResultMap.success();
    }
}
