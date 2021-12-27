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

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysPerm:SysPerm.java v1.0 2021/12/9 10:16 上午 wengchengjian Exp $
 */
@Data
@TableName("sys_perm")
public class SysPerm extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableField("name")
    private String name;

    @TableField("parent_id")
    private Integer parentId;

    private Integer menuId;

}
