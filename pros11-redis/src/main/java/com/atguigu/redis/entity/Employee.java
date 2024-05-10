package com.atguigu.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @PACKAGE_NAME: com.atguigu.entity
 * @CLASSNAME: Employee
 * @AUTHOR: zhangsan
 * @DATE: 2024/4/10 17:16
 * @SINCE 17.0.7
 * @DESCRIPTION: Employee
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer empId;
    private String empName;
    private BigDecimal empSalary;
}
