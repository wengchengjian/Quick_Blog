/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.sys;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysUserToken:SysUserToken.java v1.0 2021/12/7 10:47 上午 wengchengjian Exp $
 */
@Data
@ApiModel(value = "SysUserToken" , description = "系统用户Token")
public class SysUserToken implements Serializable {
    private static final long serialVersionUID =1L;

    private Integer userId;

    private String token;
}
