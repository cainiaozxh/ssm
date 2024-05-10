package com.atguigu.mvc.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * @PACKAGE_NAME: com.atguigu.mvc.handler
 * @CLASSNAME: OrderHandler
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/25 21:19
 * @SINCE 17.0.7
 * @DESCRIPTION: OrderHandler
 */
@Controller
@RequestMapping("/order")
public class OrderHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ServletContext servletContext;

    @RequestMapping("/dispatcher1")
    public String requestDispatcher1(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        System.out.println("contextPath = " + contextPath);
        //request.getRequestDispatcher("/WEB-INF/templates/target.html").forward(request,response)
        return "target";
    }

    @RequestMapping("/dispatcher2")
    public String requestDispatcher2(HttpServletRequest request,
                                     HttpServletResponse response) throws UnsupportedEncodingException {
        //request.setCharacterEncoding("utf-8");
        //response.setContentType("text/html;charset=utf-8");
        logger.debug("转发到target.html");
        //forward前缀,必须写完整路径
        return "forward:/WEB-INF/templates/target.html";
    }

    @RequestMapping("/dispatcher3")
    public String requestDispatcher3() {
        //转发只可以跳转到当前项目
        return "forward:www.bing.com";
    }

    @RequestMapping("/dispatcher4")
    public String requestDispatcher4() {
        return "forward:/outer.jsp";
    }

    @RequestMapping("/redirect1")
    public String redirect1() {
        //重定向到互联网的任何位置
        return "redirect:https://www.bing.com";
    }

    @RequestMapping("/redirect2")
    public String redirect2(HttpServletRequest request) {
        // 不可以重定向到WEB-INF目录下
        // 源服务器未能找到目标资源的表示或者是不愿公开一个已经存在的资源表示。
        String contextPath = request.getContextPath();
        System.out.println("contextPath = " + contextPath);
        return "redirect:/WEB-INF/templates/target.html";
    }

    @RequestMapping("/redirect3")
    public String redirect3(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        System.out.println("contextPath = " + contextPath);
        //和web中不同，此时的/不代表当前服务器，而是代表当前项目
        // 即/表示的不是浏览器的根（主机地址加端口号），而是表示 web项目上下文虚拟路径
        return "redirect:/outer.jsp";
    }

    @RequestMapping("/redirect4")
    public String redirect4() {
        logger.debug("重定向到首页index.jsp");
        //可以重定向到非WEB-INF的资源(controller,webapp);
        //和web中不同,此时的/代表的不是浏览器的根(主机地址加端口号),
        //而是表示服务器的根,(主机地址+端口号+项目上下文路径)
        return "redirect:/";
    }

    /**
     * 获取原生Servlet API
     * HttpServletRequest, HttpServletResponse, HttpSession,直接提供参数即可使用,
     * ServletContext:
     * 1,自动装配(Spring IOC容器中已经有实例),直接从容器中取出来用
     * 2,从session中获取,session.getServletContext
     * 注意:
     * 1.在request域中传递数据,使用属性域,在web组件直接传递数据
     * 2.在session域,application域中传递数据,使用原生api,HttpSession,servletContext
     *
     * @return
     */
    @RequestMapping("/getServletAPI")
    public String getServletAPI(
            HttpServletRequest req,
            HttpServletResponse resp,
            HttpSession httpSession
    ) {
        //向request域中添加数据
        req.setAttribute("msg", "用户名或密码错误");
        //向session域中添加数据
        httpSession.setAttribute("user", "zhangsan");
        //application域中添加数据
        servletContext.setAttribute("count", 100);
        httpSession.getServletContext().setAttribute("count2", 200);
        return "target";
    }


}
