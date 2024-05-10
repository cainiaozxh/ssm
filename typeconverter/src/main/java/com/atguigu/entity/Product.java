package com.atguigu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @PACKAGE_NAME: com.atguigu.entity
 * @CLASSNAME: Product
 * @AUTHOR: zhangsan
 * @DATE: 2024/2/9 19:01
 * @SINCE 17.0.7
 * @DESCRIPTION: Product 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date productDate;

    @NumberFormat(pattern = "###,###,###.###")
    private Double productPrice;

    private Address address;

    @Size(min = 7,max = 20)
    @Email
    private String email;

}
