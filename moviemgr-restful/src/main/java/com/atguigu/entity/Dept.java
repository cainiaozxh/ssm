package com.atguigu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PACKAGE_NAME: com.atguigu.entity
 * @CLASSNAME: Dept
 * @AUTHOR: zhangsan
 * @DATE: 2024/2/8 13:35
 * @SINCE 17.0.7
 * @DESCRIPTION: Dept 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    private Integer deptno;
    private String dname;
}
