package com.atguigu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @PACKAGE_NAME: com.atguigu.entity
 * @CLASSNAME: Dog
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/28 17:23
 * @SINCE 17.0.7
 * @DESCRIPTION: Dog 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("Dog")
public class Dog implements Serializable {
    private Integer dogId;
    private String dogName;
    private Integer age;
    private Integer deptId;
    /**
     * 关联映射 一对一
     */
    //private Dept dept;
}
