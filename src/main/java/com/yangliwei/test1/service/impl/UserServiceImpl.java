package com.yangliwei.test1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangliwei.test1.common.JwtUtil;
import com.yangliwei.test1.common.RedisCache;
import com.yangliwei.test1.config.error.CustomException;
import com.yangliwei.test1.config.error.CustomExceptionType;
import com.yangliwei.test1.constant.Constant;
import com.yangliwei.test1.entity.Phone;
import com.yangliwei.test1.entity.User;
import com.yangliwei.test1.mapper.UserMapper;
import com.yangliwei.test1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author ylw12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

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

    /**
     * 用户登录
     * @param user user 对象
     * @return jwt token
     */
    @Override
    public String login(User user) {
        // 1,使用AuthenticationManager类的 authenticate方法进行认证,如果认证成功，返回一个Authentication对象，否则返回null
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        // 2, 如果没通过抛出异常
        if(Objects.isNull(authenticate)){
            throw new CustomException(CustomExceptionType.USER_LOGIN_EXCEPTION);
        }
        // 3,如果通过使用jwt生成token,把token返回给前端
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJwt(id);
        // 4,redis中存储token,userid作为key
        redisCache.setCacheObject(Constant.REDIS_LOGIN_KEY +id, loginUser);
        return jwt;
    }

    /**
     *  退出登录
     * @return string
     */
    @Override
    public String logout() {
        //获取 SecurityContextHolder中的用户信息
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();
        // 删除redis中的token
        redisCache.deleteObject(Constant.REDIS_LOGIN_KEY + id);
        // 删除SecurityContextHolder中的用户信息
        SecurityContextHolder.clearContext();
        return "退出成功!";
    }

}
