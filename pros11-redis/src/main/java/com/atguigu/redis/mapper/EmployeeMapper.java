package com.atguigu.redis.mapper;

import com.atguigu.redis.entity.Employee;

import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.redis.mapper
 * @CLASSNAME: EmployeeMapper
 * @AUTHOR: zhangsan
 * @DATE: 2024/4/10 17:30
 * @SINCE 17.0.7
 * @DESCRIPTION: TODO
 */
public interface EmployeeMapper {
    public List<Employee> findAll();
}
