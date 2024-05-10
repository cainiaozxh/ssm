package com.atguigu.mvc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.mvc.pojo
 * @CLASSNAME: Paige
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/26 11:39
 * @SINCE 17.0.7
 * @DESCRIPTION: Paige javabean
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paige {
    private Integer paigeId;

    private String paigeName;

    private Season season;

    private List<Season> seasonList;
}
