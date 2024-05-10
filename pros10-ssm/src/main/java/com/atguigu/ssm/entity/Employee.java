package com.atguigu.ssm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @PACKAGE_NAME: com.atguigu.ssm.entity
 * @CLASSNAME: Employee
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/30 22:05
 * @SINCE 17.0.7
 * @DESCRIPTION: Employee
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    private Integer empId;
    private String empName;
    private Double empSalary;
}
