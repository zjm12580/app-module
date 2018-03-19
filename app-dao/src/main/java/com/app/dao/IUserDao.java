package com.app.dao;

import com.app.entity.PageParameter;
import com.app.entity.User;

import java.util.List;

/**
 * Created by zhujiamin on 2017/11/21.
 */
public interface IUserDao {

    List<User> getUser(PageParameter pageParameter);

    int updateUser(User user);

    int deleteUser(User user);

    int insertUser(User user);

    List<User> getUsers(User userparam);

    Integer countUser(User userparam);

    int batchDelete(List<Integer> idList);
}
