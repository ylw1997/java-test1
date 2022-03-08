package com.yangliwei.test1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangliwei.test1.common.AjaxResult;
import com.yangliwei.test1.common.BaseController;
import com.yangliwei.test1.entity.User;
import com.yangliwei.test1.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ylw12
 */
@Slf4j
@RestController()
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    public AjaxResult getUser(@PathVariable String id){
        return AjaxResult.success(userService.getUserById(id));
    }

    @GetMapping("/age/{age}")
    public AjaxResult getUsersByAge(@PathVariable Integer age){
        return AjaxResult.success(userService.getUsersByAge(age));
    }

    @GetMapping("/list/{page}/{size}")
    public AjaxResult getUsers(@PathVariable Integer page, @PathVariable Integer size){
        Page<User> pageObj = new Page<>(page,size);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        Page<User> userPage = userService.page(pageObj, userQueryWrapper);
        return AjaxResult.success(userPage);
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
            if(userService.count(queryWrapper)>0) {
                return AjaxResult.error("已经存在该用户,请换个名字");
            }
        }
        return toAjax(userService.save(user));
    }

    @PutMapping
    public AjaxResult updateUser(@RequestBody User user){
        if(user.getId() == null){
            return AjaxResult.error("缺少id");
        }
        return toAjax(userService.updateById(user));
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteUser(@PathVariable Long id){
        return toAjax(userService.removeById(id));
    }

}
