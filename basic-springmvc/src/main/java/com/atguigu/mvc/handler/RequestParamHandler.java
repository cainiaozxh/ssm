package com.atguigu.mvc.handler;

import com.atguigu.mvc.pojo.Employee;
import com.atguigu.mvc.pojo.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.mvc.handler
 * @CLASSNAME: RequestParamHandler
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/25 15:17
 * @SINCE 17.0.7
 * @DESCRIPTION: 获取请求参数
 */
@Controller
@RequestMapping("/param")
public class RequestParamHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 使用@RequestParam注解标记handler方法的形参
     * springmvc 会将获取到的请求参数从形参位置给我们传进来
     *
     * @param username
     * @return
     */
    @RequestMapping("/one/one")
    public String oneNameOneValue(@RequestParam("userName") String username) {
        logger.debug("获取到请求参数: " + username);
        return "target";
    }

    /**
     * 当请求参数名和形参名一致,可以省略@RequestParam("username"),括号里面是请求参数名
     * 但是,省略后代码可读性下降而且将来在SpringCloud中不能省略,建议不要省略
     *
     * @param username
     * @return
     */
    @RequestMapping("/one/one/2")
    public String oneNameOneValue2(String username) {
        logger.debug("获取到请求参数: " + username);
        return "target";
    }

    @RequestMapping("/one/one/3")
    public String oneNameOneValue3(
            @RequestParam(value = "username", required = false) String username) {
        logger.debug("获取请求参数: " + username);
        return "target";
    }

    /**
     * 有参数则用请求参数,没有参数则用@RequestParam的默认值,与required=true或者false无关
     *
     * @param username
     * @return
     */
    @RequestMapping("/one/one/4")
    public String oneNameOneValue4(
            @RequestParam(value = "username", required = false, defaultValue = "zhangsan") String username
    ) {
        logger.debug("获取请求参数: " + username);
        return "target";
    }

    /**
     * 有参数则用请求参数,没有参数则用@RequestParam的默认值,与required=true或者false无关
     *
     * @param username
     * @return
     */
    @RequestMapping("/one/one/5")
    public String oneNameOneValue5(
            @RequestParam(value = "username", required = true, defaultValue = "张三") String username
    ) {
        logger.debug("获取请求参数: " + username);
        return "target";
    }

    /**
     * 获取表单的多选框数据
     * @return
     */
    @RequestMapping("/getTeams")
    public String getTeams(
            @RequestParam("team") List<String> teamList
    ) {
        logger.debug(teamList.toString());
        return "target";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(Employee employee) {
        logger.debug("实体类对象employee = " + employee);
        return "target";
    }

    @PostMapping("/addStudent")
    public String addStudent(Student student) {
        logger.debug("实体类对象student = " + student);
        return "target";
    }

    @GetMapping("/getHeaders")
    public String getHeaders(
            @RequestHeader("Referer") String referer,
            @RequestHeader("accept") String accept
    ) {
        logger.debug("从哪里来: " + referer);
        logger.debug("响应类型: " + accept);
        return "target";
    }

    @GetMapping("/getCookies")
    public String getCookies(
            @CookieValue(value = "JSESSIONID", defaultValue = "no exists") String jsessionId,
            HttpSession httpSession
    ) {
        //JSESSIONID=4A23351A7F74ACBF6DA2956FFF28C8D0
        logger.debug("jsessionId = " + jsessionId);
        logger.debug("httpSession = " + httpSession);
        return "target";
    }
}
