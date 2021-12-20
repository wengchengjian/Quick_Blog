/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.sys.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.weng.quick_blog.entity.sys.SysRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysUserVO:SysUserVO.java v1.0 2021/12/15 3:02 下午 wengchengjian Exp $
 */
@Data
public class SysUserVO implements Serializable {
    private static final long serialVersionUID =1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String appellation;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "创建者Id")
    private Integer createUserId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "0禁用，1正常")
    private Integer status;

    @TableField(exist=false)
    private List<SysRole> roleList;

    private Integer locked;

    private Integer enabled;

    private Integer accountExpired;

    private Integer deleted;

    private Integer passwordExpired;
}
