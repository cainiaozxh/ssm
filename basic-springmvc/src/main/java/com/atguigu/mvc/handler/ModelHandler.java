package com.atguigu.mvc.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @PACKAGE_NAME: com.atguigu.mvc.handler
 * @CLASSNAME: ModelHandler
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/26 9:14
 * @SINCE 17.0.7
 * @DESCRIPTION: ModelHandler
 *
 * 属性域(Model)
 * 在request域中传递数据,使用属性域,在web组件中直接传递数据
 * 在session域,application域中传递数据,使用原生api,HttpSession,ServletContext
 */
@Controller
@RequestMapping("/model")
public class ModelHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 1.在形参位置声明Model类型变量,用于存储模型数据到request域中
     * 2.我们将数据存入模型,springmvc会帮我们把模型数据存入到请求域
     * 3.存入请求域这个动作也被称为暴漏到请求域
     * @param model
     * @return
     */
    @RequestMapping("/useModel")
    public String useModel(Model model) {
        model.addAttribute("requestScopeMessageModel", "用户名或者密码错误-[Model类型变量]");
        logger.debug("[RequestScope]-[Model]-已经将数据存入到request域中");
        return "target";
    }

    /**
     * 形参使用Map类型的变量,存储数据模型
     * @param map
     * @return
     */
    @RequestMapping("/useMap")
    public String useMap(Map<String, Object> map) {
        map.put("requestScopeMessageMap", "用户名或者密码错误-[Map]");
        logger.debug("[RequestScope]-[Map]-已经将数据存入到request域中");
        return "target";
    }

    /**
     * 形参使用ModelMap类型的变量存储数据模型
     * @param modelMap
     * @return
     */
    @RequestMapping("/useModelMap")
    public String useModelMap(ModelMap modelMap) {
        modelMap.addAttribute("requestScopeMessageModelMap", "用户名或者密码错误-[ModelMap]");
        logger.debug("[RequestScope]-[ModelMap]-已经将数据存入到request域中");
        return "target";
    }

    /**
     * 使用ModelAndView实例来存储数据模型
     * @return
     */
    @RequestMapping("/useModelAndView")
    public ModelAndView useModelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("modelAndViewMessage", "用户名或者密码错误-[ModelAndView]");
        modelAndView.setViewName("target");
        logger.debug("[RequestScope]-[ModelAndView]-已经将数据存入到request域中");
        return modelAndView;
    }


}
