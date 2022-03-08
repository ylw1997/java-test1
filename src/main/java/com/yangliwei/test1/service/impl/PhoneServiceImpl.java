package com.yangliwei.test1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangliwei.test1.entity.Phone;
import com.yangliwei.test1.mapper.PhoneMapper;
import com.yangliwei.test1.service.IPhoneService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangliwei
 * @since 2022-03-07
 */
@Service
public class PhoneServiceImpl extends ServiceImpl<PhoneMapper, Phone> implements IPhoneService {
}
