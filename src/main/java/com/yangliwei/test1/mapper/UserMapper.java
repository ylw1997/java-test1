package com.yangliwei.test1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yangliwei.test1.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ylw12
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户信息
     * @param id id
     * @return User
     */
    User selectUserById(@Param("id") String id);

    /**
     * 根据年龄查询用户信息
     * @param age age
     * @return  User
     */
    List<User> selectUsersByAge(@Param("age") Integer age);
}
