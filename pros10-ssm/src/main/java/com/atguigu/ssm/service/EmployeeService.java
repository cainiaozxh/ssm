package com.atguigu.ssm.service;

import com.atguigu.ssm.entity.Employee;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.ssm.service
 * @CLASSNAME: EmployeeService
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/31 6:45
 * @SINCE 17.0.7
 * @DESCRIPTION: EmployeeService
 */

public interface EmployeeService {
    public List<Employee> findAll();

    public int saveEmp(Employee employee);

    /**
     * 用于测试分布式事务
     * @param employee1
     * @param employee2
     * @return
     */
    public int saveEmpTx(Employee employee1, Employee employee2);

    Employee getEmpById(Integer empId);

    boolean updateEmp(Employee employee);

    boolean deleteEmpById(Integer empId);

    /**
     * 分页查询,员工
     * @param pageNum 当前页数
     * @param pageSize 每页显示的条数
     * @return
     */
    PageInfo<Employee> findEmpPagination(Integer pageNum, Integer pageSize);
}
