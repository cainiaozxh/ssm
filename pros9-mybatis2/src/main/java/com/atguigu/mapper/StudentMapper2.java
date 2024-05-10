package com.atguigu.mapper;

import com.atguigu.entity.Student;

import javax.annotation.Resource;
import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.mapper
 * @CLASSNAME: StudentMapper2
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/30 11:00
 * @SINCE 17.0.7
 * @DESCRIPTION: StudentMapper2
 */
public interface StudentMapper2 {

    public int saveStudent(Student student);


    public List<Student> findAllStudent_1();

    public List<Student> findAllStudent_2();
}
