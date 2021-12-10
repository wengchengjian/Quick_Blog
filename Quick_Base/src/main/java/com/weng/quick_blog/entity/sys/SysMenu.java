/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统菜单
 * </p>
 * @author wengchengjian
 * @version sysmenu:SysMenu.java v1.0 2021/12/7 10:46 上午 wengchengjian Exp $
 */
@Data
@ApiModel(value = "SysMenu对象" ,description = "菜单管理")
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    @ApiModelProperty(value = "父菜单ID，一级菜单为0")
    private Integer parentId;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单URL")
    private String url;



    @ApiModelProperty(value = "类型   0：目录   1：菜单 ")
    private Integer type;


    /**
     * 父菜单名称
     */
    @TableField(exist=false)
    private String parentName;
    /**
     * z-tree属性
     */
    @TableField(exist=false)
    private Boolean open;


    @TableField(exist=false)
    private List<?> list;
}
