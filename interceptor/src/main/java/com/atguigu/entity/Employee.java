package com.atguigu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @PACKAGE_NAME: com.atguigu.entity
 * @CLASSNAME: Employee
 * @AUTHOR: zhangsan
 * @DATE: 2024/2/8 11:32
 * @SINCE 17.0.7
 * @DESCRIPTION: Employee实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer empId;
    private String empName;
    private BigDecimal empSalary;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "UTC+8")
    private LocalDateTime hireDateTime;

    private Dept dept;
}
