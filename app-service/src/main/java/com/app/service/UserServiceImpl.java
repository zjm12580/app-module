package com.app.service;

import com.app.dao.IUserDao;
import com.app.entity.PageParameter;
import com.app.entity.User;
import com.app.iService.IUserService;
import com.app.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * com.app
 *
 * @author zhujiamin
 * @date 2017/7/26
 */

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDaoImpl;

        @Autowired
    private UserMapper userMapper;


    public void test() {
        System.out.println("11111");
    }


    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public List<User> getAllUsersPageList(PageParameter pageParameter) {
        return userMapper.getAllUsersPageList(pageParameter);
    }

    @Override
    public List<User> getUser(PageParameter pageParameter) {
        return userDaoImpl.getUser(pageParameter);
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public int deleteUser(User user) {
        return 0;
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }

}
