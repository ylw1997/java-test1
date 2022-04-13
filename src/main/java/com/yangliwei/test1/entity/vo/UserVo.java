package com.yangliwei.test1.entity.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 *  用户信息
 * @author yangliwei
 */
@Data
@ToString
public class UserVo implements Serializable {
    private Long id;
    private String name;
    private Integer age;
}
