package com.atguigu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PACKAGE_NAME: com.atguigu.entity
 * @CLASSNAME: Address
 * @AUTHOR: zhangsan
 * @DATE: 2024/2/10 11:34
 * @SINCE 17.0.7
 * @DESCRIPTION: Address 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String province;

    private String city;

    private String county;
}
