package com.yangliwei.test1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangliwei.test1.entity.Phone;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangliwei
 * @since 2022-03-07
 */
public interface IPhoneService extends IService<Phone> {
    /**
     *  根据品牌查询手机
     * @param brand 品牌
     * @return 手机
     */
    Phone selectByBrand(String brand);
}
