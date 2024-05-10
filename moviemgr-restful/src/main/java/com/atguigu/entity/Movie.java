package com.atguigu.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @PACKAGE_NAME: com.atguigu.entity
 * @CLASSNAME: Movie
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/27 10:38
 * @SINCE 17.0.7
 * @DESCRIPTION: Movie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "电影必须有电影编号")
    private String movieId;

    @NotBlank(message = "电影名不为空")
    private String movieName;

    @NotNull
    @Min(value = 0,message = "电影票价格必须为正")
    private BigDecimal moviePrice;
}
