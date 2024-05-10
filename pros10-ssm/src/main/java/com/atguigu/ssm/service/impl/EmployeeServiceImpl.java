package com.atguigu.ssm.service.impl;

import com.atguigu.ssm.entity.Employee;
import com.atguigu.ssm.mapper.EmployeeMapper;
import com.atguigu.ssm.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.ssm.service.impl
 * @CLASSNAME: EmployeeServiceImpl
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/31 6:46
 * @SINCE 17.0.7
 * @DESCRIPTION: EmployeeServiceImpl
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    @Transactional(rollbackFor = Exception.class,
            propagation = Propagation.REQUIRED,
            readOnly = true)
    public List<Employee> findAll() {
        List<Employee> allEmployee = employeeMapper.findAllEmployee();
        return allEmployee;
    }

    @Override
    public int saveEmp(Employee employee) {
        int i = employeeMapper.saveEmp(employee);
        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int saveEmpTx(Employee employee1, Employee employee2) {
        int i = employeeMapper.saveEmp(employee1);
        int x = 1/0;
        int i1 = employeeMapper.saveEmp(employee2);
        return i+i1;
    }

    @Override
    public Employee getEmpById(Integer empId) {
        Employee employee = employeeMapper.getEmpById(empId);
        return employee;
    }

    @Override
    public boolean updateEmp(Employee employee) {
        int i = employeeMapper.updateEmp(employee);
        return i>0;
    }

    @Override
    public boolean deleteEmpById(Integer empId) {
        int i = employeeMapper.deleteEmpById(empId);
        return i>0;
    }

    @Override
    public PageInfo<Employee> findEmpPagination(Integer pageNum, Integer pageSize) {
        //开启分页功能.开启后,后面执行的SELECT语句会自动被附加LIMIT子句,而且会自动查询总记录数
        PageHelper.startPage(pageNum, pageSize);
        //查询数据,查询所有即可
        List<Employee> allEmployee = employeeMapper.findAllEmployee();
        //返回数据
        PageInfo<Employee> pageInfo = new PageInfo<>(allEmployee);

        return pageInfo;
    }
}
