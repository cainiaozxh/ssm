package com.atguigu.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @PACKAGE_NAME: com.atguigu.util
 * @CLASSNAME: MyUtil
 * @AUTHOR: zhangsan
 * @DATE: 2024/2/10 23:54
 * @SINCE 17.0.7
 * @DESCRIPTION: MyUtil
 */
public class MyUtil {
    /**
     * 判断当前请求是否为Ajax请求
     * @param request
     * @return
     * true:当前请求是Ajax请求
     * false:当前请求不是Ajax请求
     */
//    public static boolean judgeRequestType(HttpServletRequest request) {
//        //1.获取请求头
//        String acceptHeader = request.getHeader("Accept");
//        if (acceptHeader != null && acceptHeader.contains("application/json")) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    /**
     * 判断当前请求是否是Ajax请求
     * @param request
     * @return
     *
     */
    public static boolean judgeRequestType(HttpServletRequest request) {
        //1.获取请求头
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Requested-With");
        //2.判断
        return (acceptHeader != null && acceptHeader.contains("application/json")) ||
                (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
    }
}
