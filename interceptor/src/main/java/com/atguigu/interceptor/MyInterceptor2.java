package com.atguigu.interceptor;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.atguigu.interceptor
 * @CLASSNAME: MyInterceptor1
 * @AUTHOR: zhangsan
 * @DATE: 2024/2/8 21:05
 * @SINCE 17.0.7
 * @DESCRIPTION: MyInterceptor1 拦截器1
 */
public class MyInterceptor2 implements HandlerInterceptor {
    /**
     * 哪些资源会经过拦截器
     * 1.静态资源
     * 2.分控制器的方法
     * 3.视图控制器
     * 访问这三种目标资源之前先执行该方法
     * @param request 请求
     * @param response 响应
     * @param handler 表示访问的目标资源,因为类型不同
     * @return true 放行,运行访问后面的拦截器或者目标资源
     *         false 不放行,不会再访问后面的拦截器和目标资源
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor2 的 preHandle");
        System.out.println(handler);
        return true;
    }

    /**
     * 访问这三种目标资源之后再执行该方法
     * @param request 请求
     * @param response 响应
     * @param handler 访问的目标资源,类型不同,所以是Object类型
     * @param modelAndView 访问目标资源之后的返回值
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor2 的 postHandle");
        /*ModelMap modelMap = modelAndView.getModelMap();
        modelMap.forEach((key,objectValue) -> {
            String value = (String) objectValue;
            String replaceValue = null;
            //枪支,黄色,反动的言论,需要屏蔽关键词
            if (value.contains("枪支弹药") && value.contains("黄赌毒")) {
                replaceValue = value.replace("枪支弹药", "****");
                replaceValue = replaceValue.replace("黄赌毒", "***");
            } else if (value.contains("黄赌毒")) {
                replaceValue = value.replace("黄赌毒", "***");
            } else if (value.contains("枪支弹药") ) {
                replaceValue = value.replace("枪支弹药", "****");
            } else {
                replaceValue = value;
            }
            modelAndView.addObject(key, replaceValue);
        });*/

    }

    /**
     * 访问了分控制器方法之后,并不是整个springMVC处理流程的结束,具体流程如下:
     * preHandle
     * 访问目标资源
     * postHandle
     * 解析视图 result -- WEB-INF/templates/result.html
     * 渲染视图: <a th:href="@{|/movie/${movie.movieId}|}">修改</a>
     *         <a th:href="/moviemgr/movie/1">修改</a>
     * afterCompletion 执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor2 的 afterCompletion");
    }
}
