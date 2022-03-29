package com.yangliwei.test1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangliwei.test1.entity.Phone;
import com.yangliwei.test1.entity.User;

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

    /**
     * 根据id查询用户手机信息
     * @param id id
     * @return Phones
     */
    List<Phone> getPhonesByUserId(Long id);

    /**
     * 根据输入报错
     * @param input input
     */
    void userTest(int input);

    /**
     *  根据用户名查询用户信息
     * @param username username
     * @return User
     */
    User findByUsername(String username);

    /**
     *  登录方法
     * @param user user
     * @return User
     */
    String login(User user);

    /**
     *  退出
     * @return String
     */
    String logout();

    /**
     *  根据用户id查询用户权限
     * @param id id
     * @return List<String>
     */
    List<String> findPermissionByUserId(Long id);
}
