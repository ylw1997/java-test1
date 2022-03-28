package com.yangliwei.test1.service.impl;

import com.yangliwei.test1.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *  用户详情服务实现类
 *  spring security需要实现UserDetailsService接口,来获取用户信息
 * @author yangliwei
 */
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户信息
     * @throws UsernameNotFoundException 用户名不存在异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 查询用户信息
        User user = userService.findByUsername(username);
        log.info("用户信息：{}", user);
        // 判断用户是否存在
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }
        //查询对应权限
        List<String> permissions = new ArrayList<>(Arrays.asList("user:add", "user:delete","home"));
        return new LoginUser(user,permissions);
    }
}
