package com.atguigu.mvc.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PACKAGE_NAME: com.atguigu.mvc.pojo
 * @CLASSNAME: Employee
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/25 17:07
 * @SINCE 17.0.7
 * @DESCRIPTION: Employee实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer empId;

    private String empName;

    private Integer empAge;

    private Double empSalary;

}
