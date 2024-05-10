package com.atguigu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @PACKAGE_NAME: com.atguigu.entity
 * @CLASSNAME: Employee
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/23 7:33
 * @SINCE 17.0.7
 * @DESCRIPTION: Employee实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer empId;
    private String empName;
    private Double empSalary;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private LocalDateTime hireDateTime;

    private Dept dept;
}
