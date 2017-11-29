package com.app.mapper;

import com.app.entity.PageParameter;
import com.app.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * com.app.mapper
 *
 * @param ${param}
 * @author zhujiamin
 * @date 2017/7/26
 */
@Repository
public interface UserMapper {

    public List<User> getAllUsers();

    /**
     * 分页查询数据
     * @param pageParameter
     */
    public List<User> getAllUsersPageList(PageParameter pageParameter);

    /**
     * 分页查询数据
     * @param pageParameter
     */
    List<User> getUser(PageParameter pageParameter);

    /**
     * 修改数据
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 删除数据
     * @param user
     * @return
     */
    int deleteUser(User user);

    /**
     * 插入用户
     * @param user
     * @return
     */
    int insertUser(User user);
}
