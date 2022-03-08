package com.yangliwei.test1.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author ylw12
 */
@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO,value = "id")
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic(value = "0",delval = "1")
    @TableField(fill = FieldFill.INSERT,select = false)
    private Integer isShow;
}
