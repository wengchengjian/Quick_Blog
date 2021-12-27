/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.weng.quick_blog.common.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysRolePerm:SysRolePerm.java v1.0 2021/12/9 10:39 上午 wengchengjian Exp $
 */
@Data
@TableName("sys_role_perm")
@NoArgsConstructor
public class SysRolePerm extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    @TableField("role_id")
    private Integer roleId;

    @TableField("perm_id")
    private Integer permId;

    public SysRolePerm(Integer roleId,Integer permId){
        this.roleId = roleId;
        this.permId = permId;
    }
}
