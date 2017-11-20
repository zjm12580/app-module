package com.app.mapper;

import com.app.entity.PageParameter;
import com.app.entity.User;

import java.util.List;

/**
 * com.app.mapper
 *
 * @param ${param}
 * @author zhujiamin
 * @date 2017/7/26
 */
public interface UserMapper {

    public List<User> getAllUsers();

    /**
     * 分页查询数据
     * @param pageParameter
     */
    public List<User> getAllUsersPageList(PageParameter pageParameter);
}
