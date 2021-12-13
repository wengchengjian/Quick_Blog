/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.common.request;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version PasswordRequest:PasswordRequest.java v1.0 2021/12/13 11:25 上午 wengchengjian Exp $
 */
@Data
public class PasswordRequest {
    String oldPassword;
    String newPassword;
}
