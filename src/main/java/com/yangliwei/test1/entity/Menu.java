package com.yangliwei.test1.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 *  菜单实体类
 * @author yangliwei
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_menu")
public class Menu implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String menuName;
    private String path;
    private String icon;
    private String perms;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String remark;

    @TableLogic(value = "0",delval = "1")
    @TableField(fill = FieldFill.INSERT,select = false)
    private Integer isShow;
}
