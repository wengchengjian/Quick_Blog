/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.book;

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
 * @version BookSense:BookSense.java v1.0 2021/12/3 11:03 上午 wengchengjian Exp $
 */
@Data
@ApiModel(value = "BookSense对象",description = "读后感")
public class BookSense extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "关联图书Id")
    private Integer bookId;
}
