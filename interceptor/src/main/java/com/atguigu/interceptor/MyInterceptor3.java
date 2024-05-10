package com.atguigu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.atguigu.interceptor
 * @CLASSNAME: MyInterceptor3
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/23 17:48
 * @SINCE 17.0.7
 * @DESCRIPTION: MyInterceptor3 拦截器3
 */
public class MyInterceptor3 implements HandlerInterceptor {
    /**
     * 哪些资源会经过拦截器
     * 1 静态资源
     * 2 分控制器的方法
     * 3 视图控制器(分控制器执行之后返回的ModelAndView)
     * 访问这三种目标资源之前先执行该方法
     * @param request 请求
     * @param response 响应
     * @param handler Object 访问的目标资源 因为类型不同,所以使用Object来定义
     * @return true 放心,运行访问后面的拦截器或者目标资源
     *         false 不放行,不会再访问后面的拦截器或者目标资源
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor3 preHandle");
        System.out.println("目标资源 handler = " + handler);
        return true;
    }

    /**
     * 访问这三种目标资源之后再执行该方法
     * @param request 请求
     * @param response 响应
     * @param handler Object 访问的目标资源 因为类型不同,所以使用Object类型
     * @param modelAndView 执行目标资源之后的返回值
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor3 postHandle");
        System.out.println("目标资源 handler = " + handler);
        Map<String, Object> model = modelAndView.getModel();

        for (Map.Entry<String, Object> entry : model.entrySet()) {
            //System.out.println(entry);
            String originalValue = entry.getValue().toString();
            String replaceValue = null;
            if (originalValue.contains("枪支弹药") && originalValue.contains("黄赌毒")) {
                replaceValue = originalValue.replace("枪支弹药", "****");
                replaceValue = replaceValue.replace("黄赌毒", "***");
            } else if (originalValue.contains("枪支弹药")) {
                replaceValue = originalValue.replace("枪支弹药", "****");
            } else if (originalValue.contains("黄赌毒")) {
                replaceValue = originalValue.replace("黄赌毒", "***");
            } else {
                replaceValue = originalValue;
            }
            modelAndView.addObject(entry.getKey(), replaceValue);
        }
    }

    /**
     * 访问了分控制之后不是整个SpringMVC处理流程的结束
     * 1 preHandle
     * 2 目标资源 (返回视图控制器ModelAndView)
     * 3 postHandle
     * 4 解析视图 result --WEB-INF/templates/result.html
     * 5 渲染视图,填充请求域,各个域中的数据
     * 6 afterCompletion
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor3 afterCompletion");
    }
}
