package com.app.dao.impl;

import com.app.dao.IUserDao;
import com.app.entity.PageParameter;
import com.app.entity.User;
import com.app.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhujiamin on 2017/11/21.
 */
@Service
public class UserDaoImpl implements IUserDao {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUser(PageParameter pageParameter) {
        return userMapper.getUser(pageParameter);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUser(User user) {
        return userMapper.deleteUser(user);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }
}
