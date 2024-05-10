package com.atguigu.mvc.handler;

import com.atguigu.mvc.pojo.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @PACKAGE_NAME: com.atguigu.mvc.handler
 * @CLASSNAME: EmployeeController
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/29 15:42
 * @SINCE 17.0.7
 * @DESCRIPTION: EmployeeController
 */
@Slf4j
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping
    public String findAll() {
        log.info("---EmployeeController.findAll()---");
        log.warn("---EmployeeController.findAll()---");
        log.debug("---EmployeeController.findAll()---");
        return "target";
    }


    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id) {
        log.debug("---EmployeeController.findById id = " + id);
        return "target";
    }

    @GetMapping("/{empName}/{empAge}/{empSalary}")
    public String findEmp(@PathVariable("empName") String empName,
                          @PathVariable("empAge") Integer empAge,
                          @PathVariable("empSalary") Double empSalary) {
        log.debug("---EmployeeController.findEmp empName={},empAge={},empSalary={}", empName, empAge, empSalary);
        return "target";
    }

    @PostMapping("/save")
    public String saveEmp(Employee employee) {
        log.debug("---EmployeeController saveEmp: {}", employee);
        return "target";
    }

    @PutMapping("/put")
    public String updateEmp(Employee employee) {
        log.debug("---EmployeeController upadteEmp: {}", employee);
        return "target";
    }

    @DeleteMapping("/delete/{empId}")
    public String deleteEmp(@PathVariable("empId") Integer empId) {
        log.debug("---EmployeeController deleteEmp-empId: {}", empId);
        return "target";
    }
}
