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
 *
 * </p>
 * @author wengchengjian
 * @version SysRole:SysRole.java v1.0 2021/12/7 10:46 上午 wengchengjian Exp $
 */
@Data
@ApiModel(value = "SysRole对象",description = "角色")
public class SysRole extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;



    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建者ID")
    private Integer createUserId;


    @TableField(exist=false)
    private String createUserName;

    @TableField(exist=false)
    private List<String> permList;


}
