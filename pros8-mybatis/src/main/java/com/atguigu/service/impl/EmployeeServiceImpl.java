package com.atguigu.service.impl;

import com.atguigu.entity.Employee;
import com.atguigu.mapper.EmployeeMapper;
import com.atguigu.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.service.impl
 * @CLASSNAME: EmployeeServiceImpl
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/26 17:42
 * @SINCE 17.0.7
 * @DESCRIPTION: EmployeeServiceImpl 实现类
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> findAll() {
        List<Employee> employees = employeeMapper.findAll();
        return employees;
    }

    @Override
    public int saveEmployee(Employee employee) {
        int i = employeeMapper.saveEmp(employee);
        return i;
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public int saveEmployee2(Employee employee, Employee employee2) {
        int i1 = employeeMapper.saveEmp(employee);
        int i2 = employeeMapper.saveEmp(employee2);
        return i1 + i2;
    }

    @Override
    public Employee selectEmployeeByName(String empName) {
        Employee emp = employeeMapper.selectEmployeeByName(empName);
        return emp;
    }
}
