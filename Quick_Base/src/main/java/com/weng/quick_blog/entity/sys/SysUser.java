/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.weng.quick_blog.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  用户管理
 * </p>
 * @author wengchengjian
 * @version SysUser:SysUser.java v1.0 2021/12/7 10:47 上午 wengchengjian Exp $
 */
@Data
@ApiModel(value = "SysUser对象",description = "用户管理")
public class SysUser extends BaseEntity implements Serializable {
    private static final long serialVersionUID =1L;



    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String appellation;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码盐")
    private String salt;

    @ApiModelProperty(value = "创建者Id")
    private Integer createUserId;


    @TableField(exist=false)
    private List<SysRole> roleList;

    @ApiModelProperty(value = "账号是否被锁定")
    private Integer locked;

    @ApiModelProperty(value = "账号是否可用")
    private Integer enabled;

    @ApiModelProperty(value = "账号是否过期")
    private Integer accountExpired;


    @ApiModelProperty(value = "账号密码是否过期")
    private Integer passwordExpired;

}
