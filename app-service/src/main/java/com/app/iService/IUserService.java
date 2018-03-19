package com.app.iService;

import com.app.entity.PageParameter;
import com.app.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.app.iService
 *
 * @param ${param}
 * @author zhujiamin
 * @date 2017/7/26
 */
public interface IUserService {

    public List<User> getAllUsers();

    /**
     * 分页展示数据
     * @param pageParameter
     */
    public List<User> getAllUsersPageList(PageParameter pageParameter);


    List<User> getUser(PageParameter pageParameter);

    int updateUser(User user);

    int deleteUser(User user);

    int insertUser(User user);


    List<User> getUsers(User userparam);

    Integer countUser(User userparam);

    int batchDelete(List<Integer> idList);
}
