package com.app.service;

import com.app.dao.IUserDao;
import com.app.entity.PageParameter;
import com.app.entity.User;
import com.app.iService.IUserService;
import com.app.mapper.UserMapper;
import com.app.utils.SexEnum;
import com.app.utils.StringUtils;
import com.app.utils.StringUtilsNew;
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
        return userDaoImpl.updateUser(user);
    }

    @Override
    public int deleteUser(User user) {
        return userDaoImpl.deleteUser(user);
    }

    @Override
    public int insertUser(User user) {
        return userDaoImpl.insertUser(user);
    }

    @Override
    public List<User> getUsers(User userparam) {
        List<User> userList = userDaoImpl.getUsers(userparam);
        for (User user : userList) {
            user.setSexName(SexEnum.get(user.getSex()));
            user.setPhone(StringUtilsNew.telephoneDesensitization(user.getPhone()));
            user.setRealName(StringUtilsNew.nameDesensitization2(user.getRealName()));
        }
        return userList;
    }

    @Override
    public Integer countUser(User userparam) {
        return userDaoImpl.countUser(userparam);
    }

    @Override
    public int batchDelete(List<Integer> idList) {
        return userDaoImpl.batchDelete(idList);
    }

}
