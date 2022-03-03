package com.yangliwei.test1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yangliwei.test1.common.AjaxResult;
import com.yangliwei.test1.common.BaseController;
import com.yangliwei.test1.mapper.UserMapper;
import com.yangliwei.test1.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ylw12
 */
@Slf4j
@RestController()
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public User getUser(){
        User user = new User();
        user.setName("yangliwei");
        user.setAge(20);
        user.setId(123123L);
        return user;
    }

    @GetMapping("/list")
    public AjaxResult getUsers(){
        return AjaxResult.success(userMapper.selectList(null));
    }


    /**
     * 新增用户
     * @param user 用户
     * @return 成功
     */
    @PostMapping
    public AjaxResult insertUser (@RequestBody User user){
        log.info("添加user"+user);
        String name = user.getName();

        if(name != null ){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.select().eq("name",name);
            if(userMapper.selectCount(queryWrapper)>0) {
                return AjaxResult.error("已经存在该用户,请换个名字");
            }
        }
        return toAjax(userMapper.insert(user));
    }

    @PutMapping
    public AjaxResult updateUser(@RequestBody User user){
        if(user.getId() == null){
            return AjaxResult.error("缺少id");
        }
        return toAjax(userMapper.updateById(user));
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteUser(@PathVariable Long id){
        return toAjax(userMapper.deleteById(id));
    }

}
