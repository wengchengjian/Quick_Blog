/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.operation;

import com.baomidou.mybatisplus.annotation.TableName;
import com.weng.quick_blog.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version tag:Tag.java v1.0 2021/12/2 2:13 下午 wengchengjian Exp $
 */
@Data
@TableName("tag")
@ApiModel(value="Tag对象",description="标签")
public class Tag extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty("标签名字")
    private String name;

    @ApiModelProperty("所属类型：0文章，1阅读")
    private Integer type;
}
