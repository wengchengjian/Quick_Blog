/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.operation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version tagLink:TagLink.java v1.0 2021/12/2 2:48 下午 wengchengjian Exp $
 */
@Data
@TableName("tag_link")
@ApiModel(value = "TagLink对象",description = "标签多对多维护表")
@NoArgsConstructor
public class TagLink implements Serializable {
    private static final long serialVersionUID =1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer tagId;

    private Integer linkId;

    private Integer type;

    public TagLink(Integer linkId,Integer tagId,Integer type){
        this.linkId = linkId;
        this.tagId = tagId;
        this.type = type;
    }
}
