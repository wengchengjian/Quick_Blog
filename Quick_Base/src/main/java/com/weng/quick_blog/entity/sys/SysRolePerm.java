/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
public class SysRolePerm implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    @TableField("role_id")
    private Integer roleId;

    @TableField("perm_id")
    private Integer permId;
}
