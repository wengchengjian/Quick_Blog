/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.operation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.weng.quick_blog.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version category:Category.java v1.0 2021/12/1 9:27 下午 wengchengjian Exp $
 */
@Data
@TableName("category")
public class Category extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="分类名称")
    @NotBlank
    @TableField(value = "name")
    private String name;


    @ApiModelProperty(value="主键")
    @TableField(value = "parent_id")
    @NotNull(message="父主键不能为空")
    @Min(0)
    private Integer parentId;

    @TableField(exist=false)
    private String parentName;

}
