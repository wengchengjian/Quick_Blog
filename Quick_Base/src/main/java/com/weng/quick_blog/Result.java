/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weng.quick_blog.common.enums.ErrorEnum;
import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version result:Result.java v1.0 2021/12/1 4:16 下午 wengchengjian Exp $
 */
@Data
public class Result<T> {
    private Integer code;

    private String message;

    private T data;

    public Result(){
        this(null);
    }
    public Result(T data){
        this(0,"success");
        this.data = data;
    }
    public Result(Integer code,String message){
        this.code = code;
        this.message = message;
    }
    public static <T> Result<T> Success(T data){
        return new Result(data);
    }
    public static Result Success(){
        return new Result();
    }

    public static Result Failure(){
        return new Result(-1,"error");
    }
    public static Result Failure(String message){
        return new Result(-1,message);
    }

    public static Result Failure(ErrorEnum errorEnum){
        return new Result(errorEnum.getCode(),errorEnum.getMsg());
    }

    public static void Failure(HttpServletResponse response, ErrorEnum noAuth) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        Result res = new Result();
        res.setCode(noAuth.getCode());
        res.setMessage(noAuth.getMsg());

        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(res));
        out.flush();
        out.close();
    }
    public static <T> void Success(HttpServletResponse response, T data) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        Result res = new Result(data);

        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(res));
        out.flush();
        out.close();
    }
}
