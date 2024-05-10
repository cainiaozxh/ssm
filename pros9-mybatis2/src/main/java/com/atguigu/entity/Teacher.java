package com.atguigu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.entity
 * @CLASSNAME: Teacher
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/29 16:58
 * @SINCE 17.0.7
 * @DESCRIPTION: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("Teacher")
public class Teacher implements Serializable {
    private Integer tId;
    private String tName;

    private Integer tAge;

    private List<Student> students;
}
