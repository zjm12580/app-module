package com.app.controller;

import com.app.entity.User;
import com.app.iService.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * com.app
 *
 * @author zhujiamin
 * @date 2017/7/26
 */

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Resource
    private IUserService iUser;


    @RequestMapping("getUsers")
    @ResponseBody
    public List<User> getAllUsers(){
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("")
        return iUser.getAllUsers();

    }
}
