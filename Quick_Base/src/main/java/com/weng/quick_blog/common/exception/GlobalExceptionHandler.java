/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.common.exception;

import com.weng.quick_blog.Result;
import com.weng.quick_blog.common.enums.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * <p>
 *  统一异常处理器
 * </p>
 * @author wengchengjian
 * @version globalExceptionHandle:GlobalExceptionHandler.java v1.0 2021/12/3 10:49 上午 wengchengjian Exp $
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(GlobalException.class)
    public Result handleCustomException(GlobalException e){
        Result res = new Result();
        res.setCode(e.getCode());
        res.setMessage(e.getMsg());
        log.error("出现自定义异常,错误码:{},错误消息:{}",e.getCode(),e.getMsg());
        return res;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e){
        log.error("出现notFound的异常,错误原因:  {}",e.getMessage());
        return Result.Failure(ErrorEnum.PATH_NOT_FOUND);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("数据库已存在该记录,错误原因:{}",e.getMessage());
        return Result.Failure(ErrorEnum.DUPLICATE_KEY);
    }



//    @ExceptionHandler(Exception.class)
//    public Result handleException(Exception e){
//        log.error("发生无法识别的异常,请处理后加入全局异常处理类,错误原因:{}",e.getMessage());
//        return Result.Failure();
//    }
    @ExceptionHandler(BindingException.class)
    public Result handleBindingException(BindingException e){
        log.error("sql参数绑定错误,错误原因:{}",e.getMessage());
        return Result.Failure();
    }
}
