package com.app.service;

import com.app.entity.PageParameter;
import com.app.entity.User;
import com.app.iService.IUser;
import com.app.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.app
 *
 * @author zhujiamin
 * @date 2017/7/26
 */

@Service
public class UserImpl implements IUser {


//    @Autowired
    private UserMapper userMapper;


    public void test(){
        System.out.println("11111");
    }


    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public List<User>  getAllUsersPageList(PageParameter pageParameter) {
        return userMapper.getAllUsersPageList(pageParameter);
    }
}
