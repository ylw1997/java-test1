package com.yangliwei.test1.mapper;

import com.yangliwei.test1.entity.Phone;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangliwei
 * @since 2022-03-07
 */
public interface PhoneMapper extends BaseMapper<Phone> {
    /**
     * 根据品牌查询
     * @param brand 品牌
     * @return Phone
     */
    Phone selectByBrand(String brand);
}
