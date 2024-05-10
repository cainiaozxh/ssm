package com.atguigu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @PACKAGE_NAME: com.atguigu.entity
 * @CLASSNAME: Employee
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/26 11:58
 * @SINCE 17.0.7
 * @DESCRIPTION: Employee 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("employee")
public class Employee implements Serializable {
    private Integer empId;
    private String empName;
    private Double empSalary;
}
