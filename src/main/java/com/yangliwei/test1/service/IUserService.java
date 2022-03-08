package com.yangliwei.test1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangliwei.test1.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ylw12
 */
public interface IUserService extends IService<User> {


    /**
     * 根据用户名查询用户信息
     * @param id id
     * @return User
     */
    User getUserById(String id);

    /**
     * 根据年龄查询用户信息
     * @param age age
     * @return Users
     */
    List<User> getUsersByAge(Integer age);
}
