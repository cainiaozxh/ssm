package com.atguigu.ssm.controller;

import com.atguigu.ssm.entity.Employee;
import com.atguigu.ssm.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @PACKAGE_NAME: com.atguigu.ssm.controller
 * @CLASSNAME: EmployeeController2
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/31 20:38
 * @SINCE 17.0.7
 * @DESCRIPTION: EmployeeController2
 */
@Slf4j
@Controller
@RequestMapping("/employee")
public class EmployeeController2 {
    @Resource
    private EmployeeService employeeService;

    /**
     * 分页查询,restful风格
     * @return
     */
    @RequestMapping("/get/{pageNum}")
    public String findEmpPagination(@PathVariable("pageNum") Integer pageNum, Model model) {
        /**
         * 每页显示的数据
         */
        Integer pageSize = 5;
        PageInfo<Employee> pageInfo = this.employeeService.findEmpPagination(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "employee2";
    }

    @RequestMapping("/page/toAdd")
    public String toAddPage() {
        return "addEmp";
    }
}
