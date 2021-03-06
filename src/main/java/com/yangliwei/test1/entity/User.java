package com.yangliwei.test1.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ylw12
 */
@Data
@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @TableId(type = IdType.AUTO,value = "id")
    private Long id;
    @NotEmpty(message = "姓名不能为空")
    private String name;
    @NotNull()
    @Min(value = 10,message = "年龄不能小于10")
    @Max(value = 100,message = "年龄不能大于100")
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(select = false)
    private String password;

    private String phoneNumber;

    @TableField(fill = FieldFill.INSERT)
    private int userType;

    @TableLogic(value = "0",delval = "1")
    @TableField(fill = FieldFill.INSERT,select = false)
    private Integer isShow;

    public User(String name,Integer age){
        this.name = name;
        this.age = age;
    }
}
