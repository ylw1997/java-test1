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

import javax.validation.Valid;

/**
 * @author ylw12
 */
@Slf4j
@RestController()
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public  AjaxResult login(@RequestBody User user){
        return AjaxResult.success(userService.login(user));
    }

    @GetMapping("/logout")
    public AjaxResult logout(){
        return AjaxResult.success(userService.logout());
    }

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public AjaxResult getUser(@PathVariable String id){
        return AjaxResult.success(userService.getUserById(id));
    }

    /**
     * 根据年龄查询用户
     * @param age 年龄
     * @return 用户
     */
    @GetMapping("/age/{age}")
    public AjaxResult getUsersByAge(@PathVariable Integer age){
        return AjaxResult.success(userService.getUsersByAge(age));
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param size 每页数量
     * @return 分页结果
     */
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
    public AjaxResult insertUser (  @RequestBody @Valid User user){
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

    /**
     * 更新用户
     * @param user 用户
     * @return  成功
     */
    @PutMapping
    public AjaxResult updateUser(  @RequestBody @Valid User user){
        if(user.getId() == null){
            return AjaxResult.error("缺少id");
        }
        return toAjax(userService.updateById(user));
    }

    /**
     * 删除用户
     * @param id 用户id
     * @return 成功
     */
    @DeleteMapping("/{id}")
    public AjaxResult deleteUser(@PathVariable Long id){
        return toAjax(userService.removeById(id));
    }

    /**
     * 根据id查询手机
     * @param id 用户id
     * @return ajaxResult
     */
    @GetMapping("/getPhone/{id}")
    public AjaxResult getPhonesByUserId(@PathVariable Long id){
        return AjaxResult.success(userService.getPhonesByUserId(id));
    }

    /**
     * 错误测试
     * @return ajaxResult
     */
    @GetMapping("/error/{id}")
    public AjaxResult error(@PathVariable int id){
        userService.userTest(id);
        return AjaxResult.success();
    }

}
