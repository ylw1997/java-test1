package com.yangliwei.test1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangliwei.test1.entity.User;
import com.yangliwei.test1.mapper.UserMapper;
import com.yangliwei.test1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ylw12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(String id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public List<User> getUsersByAge(Integer age) {
        return userMapper.selectUsersByAge(age);
    }

}
