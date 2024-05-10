package com.atguigu.mvc.handler;

import com.atguigu.mvc.pojo.Tiger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @PACKAGE_NAME: com.atguigu.mvc.handler
 * @CLASSNAME: HelloHandler
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/24 14:23
 * @SINCE 17.0.7
 * @DESCRIPTION: 处理器
 */
@Controller
public class HelloHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/toRest")
    public String toRest() {
        return "rest";
    }


    @GetMapping("/sayhello")
    public String sayHello() {
        //System.out.println("Hello,SpringMVC");
        logger.debug("Hello,我是SpringMVC");
        return "target";
    }

    @RequestMapping(value = "/fruit/*", method = RequestMethod.GET)
    public String requestMapping() {
        logger.debug("[debug]-@RequestMapping模糊匹配");
        return "target";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String restrictRequestMethod() {
        logger.debug("[debug]-@RequestMapping-同地址get请求");
        logger.debug("为什么不打印");
        return "target";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String restrictMethodPost() {
        logger.debug("[debug]-@RequestMapping-同地址post请求");
        return "target";
    }

    @RequestMapping("/save/tiger")
    public String updateTiger(Model model) {
        Tiger tiger1 = new Tiger();
        tiger1.setTigerId(1001);
        tiger1.setTigerName("东北虎");
        tiger1.setTigerSalary(1000.0);
        model.addAttribute("tiger1", tiger1);
        return "update";
    }
}
