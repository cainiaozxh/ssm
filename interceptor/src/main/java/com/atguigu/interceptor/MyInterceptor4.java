package com.atguigu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @PACKAGE_NAME: com.atguigu.interceptor
 * @CLASSNAME: MyInterceptor4
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/23 17:48
 * @SINCE 17.0.7
 * @DESCRIPTION: MyInterceptor4 拦截器4
 */
public class MyInterceptor4 implements HandlerInterceptor {
    /**
     * 哪些资源会经过拦截器
     * 1.静态资源
     * 2.分控制器的方法,
     * 3.视图控制器
     * 访问这三种目标资源之前先执行该方法preHandle
     * @param request 请求
     * @param response 响应
     * @param handler Object类型 访问的目标资源 因为类型不同 所以使用Object来定义
     * @return true 放行,运行访问后面的拦截器或者目标资源 分控制的方法
     *         false 不放行,不会再访问后面的拦截器和目标资源,直接返回数据给前端
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor4 preHandle");
        return true;
    }

    /**
     * 访问这三种目标资源之后再执行该方法
     * @param request 请求
     * @param response 响应
     * @param handler Object访问的目标资源,因为类型不同,所以使用Object来定义
     * @param modelAndView 访问目标资源的返回值,视图控制器
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor4 postHandle");
    }

    /**
     * 访问了分控制器方法之后并不是整个SpringMVC处理流程的结束
     * SpringMVC处理流程
     * 1 preHandle
     * 2 访问目标资源 返回ModelAndView视图控制器
     * 3 postHandle
     * 4 解析视图 result ----WEB-INF/templates/result.html
     * 5 渲染视图 由viewResolver 视图解析器 来渲染Model 和 View
     * <a th:href="@{|/movie/${movie.movieId}|}">修改</a>
     * 渲染之后得到
     * <a th:href="@{|/movie/1|}">修改</a>
     * 6 afterCompletion 最终执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterception4 afterCompletion");
    }
}
