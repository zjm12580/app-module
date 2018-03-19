package com.app.controller;

import com.app.entity.PageParameter;
import com.app.entity.User;
import com.app.utils.JsonUtils;
import com.app.utils.ResultMap;
import com.app.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
     * 欢迎界面
     * @return
     */
    @RequestMapping("/app/welcomeIndex")
    public String welcomeIndex() {
        return "/welcome";
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
     * @return
     */
    @RequestMapping(value = "/app/toLogin")
    @ResponseBody
    public Map toLogin(@RequestBody() User user) {
//        User user = JsonUtils.parseObject(userStr,User.class);
        PageParameter page = new PageParameter(request);
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getUserName())) {
            return ResultMap.failed("账号密码都不能为空");
        }
        //登陆逻辑


        return ResultMap.success();
    }

//    /**
//     * 登陆
//     * @param userName
//     * @param password
//     * @param indentifyCode
//     * @return
//     */
//    @RequestMapping(value = "/app/toLogin",method = RequestMethod.POST)
//    public String toLogin(String userName, String password, String indentifyCode) {
//        PageParameter page =new PageParameter(request);
////        if (StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)){
////            return "error";
////        }
//        //登陆逻辑
//        return "/index";
//    }

}
