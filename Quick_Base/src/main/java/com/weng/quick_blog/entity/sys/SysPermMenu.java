/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @version SysPermMenu:SysPermMenu.java v1.0 2021/12/10 3:22 下午 wengchengjian Exp $
 */
@Data
@TableName("sys_perm_menu")
public class SysPermMenu extends BaseEntity implements Serializable {
    private static final long serialVersionUID =1L;


    private Integer permId;

    private Integer menuId;
}
