package com.atguigu.mapper;

import com.atguigu.entity.Teacher;

import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.mapper
 * @CLASSNAME: TeacherMapper
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/29 17:04
 * @SINCE 17.0.7
 * @DESCRIPTION: TeacherMapper
 */
public interface TeacherMapper {
    /**
     * 老师对学生 一对多
     * <collection>内置</collection>
     * @return
     */
    List<Teacher> findAll_1();

    /**
     * 来实对学生 一对多
     * <collection></collection>外置
     * @return
     */
    List<Teacher> findAll_2();

    /**
     * 子查询
     * @return
     */
    List<Teacher> findAll_3();
}
