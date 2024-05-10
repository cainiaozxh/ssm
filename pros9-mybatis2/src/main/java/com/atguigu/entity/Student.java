package com.atguigu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.entity
 * @CLASSNAME: Student
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/29 16:59
 * @SINCE 17.0.7
 * @DESCRIPTION: Student 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("Student")
public class Student implements Serializable {
    private Integer sId;
    private String sName;

    private Integer sAge;

    private List<Teacher> teachers;
}
