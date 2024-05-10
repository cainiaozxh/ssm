package com.atguigu.ssm.mapper;

import com.atguigu.ssm.entity.Employee;

import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.ssm.mapper
 * @CLASSNAME: EmployeeMapper
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/30 22:06
 * @SINCE 17.0.7
 * @DESCRIPTION: EmployeeMapper
 */
public interface EmployeeMapper {
    public List<Employee> findAllEmployee();
    public int saveEmp(Employee employee);

    Employee getEmpById(Integer empId);

    int updateEmp(Employee employee);

    int deleteEmpById(Integer empId);
}
