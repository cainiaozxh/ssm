package com.atguigu.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.atguigu.entity.Dept;
import com.atguigu.entity.Dog;
import com.atguigu.entity.Student;
import com.atguigu.entity.Teacher;
import com.atguigu.mapper.DeptMapper;
import com.atguigu.mapper.DogMapper;
import com.atguigu.mapper.StudentMapper;
import com.atguigu.mapper.TeacherMapper;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.test
 * @CLASSNAME: RMTest
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/28 17:29
 * @SINCE 17.0.7
 * @DESCRIPTION: RMTest mybatis映射关系
 */
@SpringJUnitConfig(locations = "classpath:spring-persist.xml")
public class RMTest {
    @Resource
    private DruidDataSource druidDataSource;

    @Resource
    private DogMapper dogMapper;

    @Resource
    private DeptMapper deptMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private StudentMapper studentMapper;

    @Test
    public void testConnection() {
        try {
            Connection connection = druidDataSource.getConnection();
            System.out.println("connection = " + connection);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void findAll() {
        List<Dog> dogs = dogMapper.findAll();
        dogs.forEach(System.out::println);
    }

    @Test
    public void findOneToOne_1() {
        List<Dog> oneToOne1 = dogMapper.findOneToOne_1();
        oneToOne1.forEach(System.out::println);
    }

    @Test
    public void findOneToOne_2() {
        List<Dog> oneToOne2 = dogMapper.findOneToOne_2();
        oneToOne2.forEach(System.out::println);
    }

    @Test
    public void findOneToOne_3() {
        List<Dog> oneToOne3 = dogMapper.findOneToOne_3();
        oneToOne3.forEach(System.out::println);
    }

    @Test
    public void findAll2() {
        List<Dept> depts = deptMapper.findAll2();
        depts.forEach(System.out::println);
    }

    @Test
    public void findAll2_2() {
        List<Dept> all22 = deptMapper.findAll2_2();
        all22.forEach(System.out::println);
    }

    @Test
    public void findAll2_3() {
        List<Dept> all23 = deptMapper.findAll2_3();
        all23.forEach(System.out::println);
    }

    @Test
    public void teacherFindAll_1() {
        List<Teacher> all1 = teacherMapper.findAll_1();
        teacherMapper.findAll_1();
        teacherMapper.findAll_1();
        all1.forEach(System.out::println);
    }

    @Test
    public void teacherFindAll_2() {
        List<Teacher> all2 = teacherMapper.findAll_2();
        all2.forEach(System.out::println);
    }

    @Test
    public void studentFindAll_1() {
        List<Student> all1 = studentMapper.findAll_1();
        all1.forEach(System.out::println);
    }

    @Test
    public void studentFindAll_2() {
        List<Student> all2 = studentMapper.findAll_2();
        all2.forEach(System.out::println);
    }
}
