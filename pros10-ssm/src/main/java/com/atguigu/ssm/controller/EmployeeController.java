package com.atguigu.ssm.controller;

import com.atguigu.ssm.entity.Employee;
import com.atguigu.ssm.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.ssm.controller
 * @CLASSNAME: EmployeeController
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/31 7:31
 * @SINCE 17.0.7
 * @DESCRIPTION: EmployeeController
 */
@Controller
@RequestMapping("/emp")
@Slf4j
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;

    @RequestMapping("/")
    public String findAll(Model model) {
        List<Employee> employees = this.employeeService.findAll();
        model.addAttribute("empList", employees);
        return "employee";
    }

    /**
     * 跳转到新增员工页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/page/add")
    public String toPageAdd(Model model) {
        return "addEmp";
    }

    @RequestMapping("/addEmp")
    public String addEmployee(Employee employee, Model model) {
        int i = employeeService.saveEmp(employee);
        return "redirect:/emp/";
    }

    @RequestMapping("/getEmpById")
    public String getEmpById(@RequestParam("empId") Integer empId,
                             Model model) {
        Employee employee = employeeService.getEmpById(empId);
        model.addAttribute("employee", employee);
        return "updateEmp";
    }

    @RequestMapping("/updateEmp")
    public String updateEmp(Employee employee) {
        boolean b = employeeService.updateEmp(employee);
        return "redirect:/emp/";
    }

    @RequestMapping("/deleteEmp")
    public String deleteEmpById(@RequestParam("empId") Integer empId) {
        boolean b = employeeService.deleteEmpById(empId);
        return "redirect:/emp/";
    }


}
