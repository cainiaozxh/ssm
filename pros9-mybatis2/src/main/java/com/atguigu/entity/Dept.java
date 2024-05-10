package com.atguigu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.entity
 * @CLASSNAME: Dept
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/28 17:25
 * @SINCE 17.0.7
 * @DESCRIPTION: Dept 部门实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("Dept")
public class Dept implements Serializable {
    private Integer deptId;
    private String deptName;
    /**
     * 一个部门下有多个"员工"
     */
    private List<Dog> dogList;
}
