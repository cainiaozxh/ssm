package com.atguigu.controller;

import com.atguigu.entity.Dept;
import com.atguigu.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @PACKAGE_NAME: com.atguigu.controller
 * @CLASSNAME: AxiosController
 * @AUTHOR: zhangsan
 * @DATE: 2024/2/8 11:18
 * @SINCE 17.0.7
 * @DESCRIPTION: AxiosController
 */
@Slf4j
@Controller
@RequestMapping("/ajax")
public class AxiosController {

    @PostMapping("/demo1")
    @ResponseBody //响应体 将数据直接放入到响应体中返回
    public String ajaxDemo1(
            @RequestParam("empId") Integer empId,
            @RequestParam("empName") String empName,
            @RequestParam("empSalary") BigDecimal empSalary) {
        log.debug("ajaxDemo1: empId={},empName={},empSalary={}",empId, empName, empSalary);
        //不加@ResponseBody 是利用模板引擎 转发到 /WEB-INF/templates/ok.html
        //加上@ResponseBody 是直接给请求的页面返回请求的json数据,不涉及页面跳转
        //ajax本来就是局部刷新,异步访问,不涉及页面跳转
        //返回ok这个数据Ajax请求
        return "ok";
    }

    @PostMapping("/demo2")
    @ResponseBody //响应体
    public String ajaxDemo2(Employee employee) {
        log.debug("ajaxDemo2: employee = {}", employee);
        return "okk";
    }

    @PostMapping("/demo3")
    @ResponseBody //响应体
    public String ajaxDemo3(Employee employee) {
        log.debug("ajaxDemo3: employee = {}", employee);
        return "okkk";
    }

    @PostMapping("/demo4")
    @ResponseBody
    public String ajaxDemo4(@RequestBody Employee employee) {
        log.debug("ajaxDemo4: employee = {}", employee);
        return "ajaxDemo4 okkk";
    }

    @PostMapping("/demo5")
    @ResponseBody
    public Employee ajaxDemo5(@RequestBody Employee employee) {
        log.debug("ajaxDemo5: employee = {}",employee);
        Employee employee1 = new Employee();
        employee1.setEmpId(20);
        employee1.setEmpName("赵六");
        employee1.setHireDate(new Date());
        employee1.setHireDateTime(LocalDateTime.now());
        Dept dept = new Dept();
        dept.setDeptno(1003);
        dept.setDname("后勤部");
        employee1.setDept(dept);
        return employee1;
    }
}
