/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version errorEnum:ErrorEnum.java v1.0 2021/12/3 10:44 上午 wengchengjian Exp $
 */
@AllArgsConstructor
@Getter
public enum ErrorEnum {
    UNKOWN(500,"系统内部错误,请联系管理员"),
    PATH_NOT_FOUND(404,"路径不存在，请检查路径"),
    NO_AUTH(403,"没有权限，请联系管理员"),
    DUPLICATE_KEY(501,"数据库中已存在该记录"),
    TOKEN_GENERATOR_ERROR(502,"token生成失败"),
    NO_UUID(503,"uuid为空"),
    SQL_ILLEGAL(504,"sql非法"),
    NOT_LOGIN(401,"用户未登陆"),
    USER_NOT_FOUND(500,"用户名不存在"),
    USER_FREEZE(500,"用户已被冻结"),
    USER_PASSWORD_FAIL(500,"用户名或账号错误"),
    LOGIN_FAIL(500,"登录失败")
    ;
    private final int code;

    private final String msg;
}
