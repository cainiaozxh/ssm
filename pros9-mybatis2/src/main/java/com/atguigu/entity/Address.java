package com.atguigu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @PACKAGE_NAME: com.atguigu.entity
 * @CLASSNAME: Address
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/28 15:55
 * @SINCE 17.0.7
 * @DESCRIPTION: Address  实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    private String province;
    private String city;
    private String county;
}
