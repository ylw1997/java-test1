package com.yangliwei.test1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangliwei.test1.config.error.CustomException;
import com.yangliwei.test1.config.error.CustomExceptionType;
import com.yangliwei.test1.entity.Phone;
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

    @Override
    public List<Phone> getPhonesByUserId(Long id) {
        return userMapper.selectPhonesByUserId(id);
    }

    @Override
    public void userTest(int input) {
        if(input == 1){
            throw new CustomException(CustomExceptionType.PARAM_ERROR);
        }else{
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR);
        }
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

}
