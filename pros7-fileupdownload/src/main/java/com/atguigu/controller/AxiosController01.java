package com.atguigu.controller;

import com.atguigu.entity.Dept;
import com.atguigu.entity.Employee;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @PACKAGE_NAME: com.atguigu.controller
 * @CLASSNAME: AxiosController01
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/22 15:25
 * @SINCE 17.0.7
 * @DESCRIPTION: TODO
 */
@Controller
@Slf4j
@RequestMapping("/ajax")
public class AxiosController01 {
    @GetMapping("/demo1")
    public String ajaxDemo1() {
        log.debug("ajax发送成功");
        //不加@ResponseBody 利用模板引擎 转发到 /WEB-INF/templates/ok.html
        //加上@ResponseBody 会直接给请求的页面返回json数据,不涉及页面跳转
        //ajax本来就是局部刷新,异步访问,不涉及页面跳转
        //返回ok这几数据Ajax请求
        return "axios01";
    }

    @GetMapping("/demo2")
    public String ajaxDemo2() {
        return "axios02";
    }

    @RequestMapping(value = "/checkUser",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ajaxDemo02(@RequestParam String username, HttpServletResponse resp) {
        log.debug(username);
        String respdata = "";
        if (!(null == username || "".equals(username))) {
            respdata = "username是 " + username + " ,用户名正确!";
        } else {
            respdata = "用户名错误";
        }
        return respdata;
    }

    @RequestMapping(value = "/checkUser2", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ajaxDemo03(@RequestBody String username) {
        log.debug(username);
        String respdata = "";
        if (!(null == username || "".equals(username))) {
            respdata = "username是 " + username + " ,用户名正确!";
        } else {
            respdata = "用户名错误";
        }
        return respdata;
    }

    @RequestMapping("/ajaxDemo1")
    @ResponseBody
    public String ajaxDemo04(@RequestParam("empId") Integer empId,
                             @RequestParam("empName") String empName,
                             @RequestParam("empSalary") double empSalary) {
        //后台项目日志
        log.debug("ajaxDemo1: " + empId + "-" + empName + "-" + empSalary);
        int i = 1/0;
        return "员工信息ok";
    }

    @PostMapping("/ajaxDemo2")
    @ResponseBody
    public String ajaxDemo05(Employee employee) {
        //后台日志
        log.debug("ajaxDemo2: " + employee);
        return "员工信息ok";
    }

    @PostMapping("/ajaxDemo3")
    @ResponseBody
    public String ajaxDemo06(Employee employee) {
        log.debug("ajaxDemo3: " + employee);
        return "员工信息ok";
    }

    @PostMapping("/ajaxDemo4")
    @ResponseBody
    public String ajaxDemo07(@RequestBody Employee employee) {
        log.debug("ajaxDemo3: " + employee);
        return "员工信息ok";
    }

    @PostMapping("ajaxDemo5")
    @ResponseBody
    public Employee ajaxDemo08() {
        Employee employee = new Employee();
        Dept dept = new Dept(1001, "研发部");
        employee.setEmpId(10);
        employee.setEmpName("张三");
        employee.setEmpSalary(456.78);
        employee.setHireDate(new Date());
        employee.setHireDateTime(LocalDateTime.now());
        employee.setDept(dept);
        return employee;
    }
}
