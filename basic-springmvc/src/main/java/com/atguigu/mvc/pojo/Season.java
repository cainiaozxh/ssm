package com.atguigu.mvc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PACKAGE_NAME: com.atguigu.mvc.pojo
 * @CLASSNAME: Season
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/26 11:38
 * @SINCE 17.0.7
 * @DESCRIPTION: Season 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Season {
    // 提交给服务器的值
    private String submitValue;
    // 给用户看的值
    private String showForUserValue;

}
