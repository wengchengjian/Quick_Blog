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
 * @version bookNote:BookNote.java v1.0 2021/12/3 11:03 上午 wengchengjian Exp $
 */
@Data
@ApiModel(value = "BookNote对象",description = "笔记")
public class BookNote extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "笔记标题")
    private String title;

    @ApiModelProperty(value = "笔记描述")
    private String description;

    @ApiModelProperty(value = "笔记作者")
    private String author;

    @ApiModelProperty(value = "笔记内容")
    private String content;

    @ApiModelProperty(value = "阅读量")
    private Long readNum;

    @ApiModelProperty(value = "点赞量")
    private Long likeNum;

    @ApiModelProperty(value = "评论量")
    private Long commentNum;

    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "所属书本")
    private Integer bookId;

    @ApiModelProperty(value = "所属章节")
    private String chapter;

    @ApiModelProperty(value = "是否推荐笔记")
    private Boolean recommend;

    @ApiModelProperty(value = "分类类别存在多级分类，用逗号隔开")
    private String categoryId;

    @ApiModelProperty(value = "发布状态")
    private Boolean publish;

    @ApiModelProperty(value = "封面类型")
    private Integer coverType;

    @ApiModelProperty(value = "是否置顶")
    private Boolean top;

    @ApiModelProperty(value = "格式化后的内容")
    private String contentFormat;
}
