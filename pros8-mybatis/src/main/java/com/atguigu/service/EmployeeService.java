package com.atguigu.service;

import com.atguigu.entity.Employee;
import com.atguigu.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.service
 * @CLASSNAME: EmployeeService
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/26 17:41
 * @SINCE 17.0.7
 * @DESCRIPTION: EmployeeService 层
 */
public interface EmployeeService {
    public List<Employee> findAll();

    public int saveEmployee(Employee employee);

    public int saveEmployee2(Employee employee, Employee employee2);

    public Employee selectEmployeeByName(String empName);

}
