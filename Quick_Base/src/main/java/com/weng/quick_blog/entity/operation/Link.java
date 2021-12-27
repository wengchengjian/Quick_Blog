/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.operation;

import com.weng.quick_blog.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;

/**
 * <p>
 *  友链
 * </p>
 * @author wengchengjian
 * @version link:Link.java v1.0 2021/12/1 4:23 下午 wengchengjian Exp $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Link对象",description="友链")
public class Link extends BaseEntity implements Serializable  {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value="链接名称")
    private String title;

    @URL
    @ApiModelProperty(value="链接地址")
    private String url;


    @ApiModelProperty(value="头像")
    private String avatar;
}
