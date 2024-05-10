package com.atguigu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @PACKAGE_NAME: com.atguigu.entity
 * @CLASSNAME: Product02
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/23 20:46
 * @SINCE 17.0.7
 * @DESCRIPTION: Product02 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product02 {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date productDate;

    @NumberFormat(pattern = "###,###,###,###")
    private Double productPrice;

    private Address address;

    @Size(min = 7,max = 20)
    @Email
    private String email;
}
