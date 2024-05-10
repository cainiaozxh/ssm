package com.atguigu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.awt.*;
import java.io.Serializable;

/**
 * @PACKAGE_NAME: com.atguigu.entity
 * @CLASSNAME: DemoUser
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/29 19:46
 * @SINCE 17.0.7
 * @DESCRIPTION: DemoUser 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("DemoUser")
public class DemoUser implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
}
