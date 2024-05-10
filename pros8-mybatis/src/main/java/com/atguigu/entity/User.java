package com.atguigu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @PACKAGE_NAME: com.atguigu.entity
 * @CLASSNAME: User
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/26 11:48
 * @SINCE 17.0.7
 * @DESCRIPTION: User
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private String name;
    private Integer age;
}
