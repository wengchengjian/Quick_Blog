/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.article;

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
 * @version article:Article.java v1.0 2021/12/2 4:07 下午 wengchengjian Exp $
 */
@Data
@ApiModel(value = "BlogArticle对象",description = "文章")
@TableName("article")
public class Article extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章描述")
    private String description;

    @ApiModelProperty(value = "文章作者")
    private String author;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "阅读量")
    private Long readNum;

    @ApiModelProperty(value = "点赞量")
    private Long likeNum;

    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "文章展示类别,0:普通，1：大图片，2：无图片")
    private Integer coverType;

    @ApiModelProperty(value = "是否推荐文章")
    private Integer recommend;

    @ApiModelProperty(value = "分类类别")
    private String categoryId;

    @ApiModelProperty(value = "发布状态")
    private Integer publish;

    @ApiModelProperty(value = "是否置顶")
    private Integer top;

    @ApiModelProperty(value = "格式化后的内容")
    private String contentFormat;

}
