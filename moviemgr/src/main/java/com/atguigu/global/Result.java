package com.atguigu.global;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * @PACKAGE_NAME: com.atguigu.global
 * @CLASSNAME: Result
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/29 9:15
 * @SINCE 17.0.7
 * @DESCRIPTION: 统一返回结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 返回对象或者集合
     */
    private T data;
    //成功码
    public static final Integer SUCCESS_CODE = 200;
    public static final String SUCCESS_MSG = "SUCCESS";

    //失败
    public static final Integer ERROR_CODE = 201;
    public static final String ERROR_MSG = "系统异常,请联系管理员";
    //没有权限的响应码
    public static final Integer NO_AUTH_CODE = 999;

    /**
     * 执行成功
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    /**
     * 执行失败,传入错误码的信息
     * @param <T>
     * @return
     */
    public static <T> Result failed(String msg) {
        msg = StringUtils.hasLength(msg)?msg:ERROR_MSG;
        return new Result<>(ERROR_CODE, msg, "");
    }

    /**
     * 执行失败,传入错误码
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result failed(Integer code, String msg) {
        msg = StringUtils.hasLength(msg)?msg:ERROR_MSG;
        return new Result<>(code, msg, "");
    }

    /**
     * 执行失败,传入错误数据
     * @param code
     * @param msg
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Result failed(Integer code, String msg, T t) {
        msg = StringUtils.hasLength(msg)?msg:ERROR_MSG;
        return new Result(code, msg, t);
    }
}
