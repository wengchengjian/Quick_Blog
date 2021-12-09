/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.operation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version recommend:Recommend.java v1.0 2021/12/2 3:33 下午 wengchengjian Exp $
 */
@Data
@ApiModel(value = "Recommand对象",description = "推荐")
@TableName("recommend")
public class Recommend implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "推荐的文章id")
    private Integer linkId;

    @ApiModelProperty(value = "推荐类型")
    private Integer type;

    @ApiModelProperty(value = "顺序")
    private Integer orderNum;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "置顶")
    private Boolean top;


}
