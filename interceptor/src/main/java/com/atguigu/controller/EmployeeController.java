package com.atguigu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @PACKAGE_NAME: com.atguigu.controller
 * @CLASSNAME: EmployeeController
 * @AUTHOR: zhangsan
 * @DATE: 2024/2/8 22:38
 * @SINCE 17.0.7
 * @DESCRIPTION: EmployeeController 分控制器
 */
@Slf4j
@Controller
@RequestMapping("/emp")
public class EmployeeController {

    @GetMapping("/findAll")
    public String findAll(Model model) {
        log.debug("----EmployeeController findAll------");
        model.addAttribute("msg1", "---枪支弹药---");
        model.addAttribute("msg2", "---黄赌毒---");
        model.addAttribute("msg3", "---实现中华民族伟大复兴的中国梦---");
        model.addAttribute("msg4", "---既有黄赌毒,又有枪支弹药---");
        return "result";
    }

    @GetMapping("/findAll2")
    public String findAll2(ModelAndView modelAndView) {
        log.debug("---EmployeeController findAll2-----");
        modelAndView.addObject("msg1", "---枪支弹药---");
        modelAndView.addObject("msg2", "---黄赌毒---");
        modelAndView.addObject("msg3", "---实现中华民族伟大复兴的中国梦---");
        modelAndView.addObject("msg4", "---既有黄赌毒,又有枪支弹药---");
        return "result";
    }
}
