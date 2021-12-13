/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.common.exception;

import com.weng.quick_blog.common.enums.ErrorEnum;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version GlobalException:GlobalException.java v1.0 2021/12/3 10:46 上午 wengchengjian Exp $
 */
@Data
public class GlobalException extends RuntimeException{
    private String msg;

    private Integer code;

    public GlobalException(){
        super(ErrorEnum.UNKOWN.getMsg());
        this.msg = ErrorEnum.UNKOWN.getMsg();
    }

    public GlobalException(ErrorEnum errorEnum,Throwable throwable){
        super(errorEnum.getMsg(),throwable);
        this.msg = errorEnum.getMsg();
        this.code = errorEnum.getCode();
    }

    public GlobalException(ErrorEnum errorEnum ){
        this.msg = errorEnum.getMsg();
        this.code = errorEnum.getCode();
    }

    public GlobalException(String msg){
        this.msg = msg;
        this.code = -1;
    }

}
