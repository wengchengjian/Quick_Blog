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
import java.util.Date;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version book:Book.java v1.0 2021/12/3 11:01 上午 wengchengjian Exp $
 */
@Data
@ApiModel(value = "Book对象",description = "图书表")
public class Book extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "分类类别")
    private String categoryId;

    @ApiModelProperty(value = "出版社")
    private String publisher;

    @ApiModelProperty(value = "出版日期")
    private Date publishDate;

    @ApiModelProperty(value = "页数")
    private int pageNum;

    @ApiModelProperty(value = "评分")
    private Double grade;

    @ApiModelProperty(value = "简介")
    private String description;

    @ApiModelProperty(value = "原书目录")
    private String catalogue;

    @ApiModelProperty(value = "阅读量")
    private Integer readNum;

    @ApiModelProperty(value = "点赞量")
    private Integer likeNum;

    @ApiModelProperty(value = "评论量")
    private Long commentNum;

    @ApiModelProperty(value = "是否推荐")
    private Boolean recommend;

    @ApiModelProperty(value = "是否发布")
    private Boolean publish;

    @ApiModelProperty(value = "读书进度")
    private Integer progress;

    @ApiModelProperty(value = "是否正在阅读")
    private Boolean reading;
}
