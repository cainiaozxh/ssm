package com.atguigu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PACKAGE_NAME: com.atguigu.entity
 * @CLASSNAME: User
 * @AUTHOR: zhangsan
 * @DATE: 2024/2/12 12:03
 * @SINCE 17.0.7
 * @DESCRIPTION: User
 * 上传照片:是将图片上传到服务器上,同时将图片名称写入到数据库表中
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;

    private String realName;

    private Integer age;
    /**
     * 照片路径
     */
    private String photo;
}
