package com.atguigu.mapper;

import com.atguigu.entity.Student;

import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.mapper
 * @CLASSNAME: StudentMapper
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/29 17:04
 * @SINCE 17.0.7
 * @DESCRIPTION: StudentMapper
 */
public interface StudentMapper {
    /**
     * 查找所有
     * @return
     */
    public List<Student> findAll_1();

    /**
     * 查找所有 resultMap 外置
     * @return
     */
    public List<Student> findAll_2();
}
