package com.yangliwei.test1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangliwei.test1.common.AjaxResult;
import com.yangliwei.test1.entity.Phone;
import com.yangliwei.test1.mapper.PhoneMapper;
import com.yangliwei.test1.service.IPhoneService;
import com.yangliwei.test1.service.impl.PhoneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangliwei
 * @since 2022-03-07
 */
@RestController
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    private IPhoneService phoneService;

    /**
     * 根据品牌查询
     * @param brand 品牌
     * @return AjaxResult
     */
    @GetMapping("/brand")
    public AjaxResult getPhoneByBrand(@RequestParam String brand){
        Phone phone = phoneService.selectByBrand(brand);
        return AjaxResult.success(phone);
    }

    /**
     * 查询所有
     * @param page 分页
     * @param size  每页显示条数
     * @return AjaxResult
     */
    @GetMapping("/list/{page}/{size}")
    public AjaxResult getPhoneList(@PathVariable Integer page, @PathVariable Integer size){
        QueryWrapper<Phone> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<Phone> pageObj = new Page<>(page,size);
        return AjaxResult.success(phoneService.page(pageObj,queryWrapper));
    }

    /**
     * 添加
     * @param phone 实体
     * @return AjaxResult
     */
    @PostMapping("")
    public AjaxResult addPhone(@RequestBody Phone phone){
        List<Phone> brand = phoneService.list(new QueryWrapper<Phone>().eq("brand", phone.getBrand()));
        if(brand.size()>0){
            return AjaxResult.error("该品牌已存在");
        }else{
            return AjaxResult.success(phoneService.save(phone));
        }
    }

    /**
     * 修改
     * @param phone 实体
     * @return AjaxResult
     */
    @PutMapping("")
    public AjaxResult updatePhone(@RequestBody Phone phone){
        return AjaxResult.success(phoneService.updateById(phone));
    }

    /**
     * 删除
     * @param id 主键
     * @return AjaxResult
     */
    @DeleteMapping("/{id}")
    public AjaxResult deletePhone(@PathVariable String id){
        return AjaxResult.success(phoneService.removeById(id));
    }

}

