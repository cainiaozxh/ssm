package com.atguigu.test;

import com.atguigu.entity.Dept;
import com.atguigu.entity.Dog;
import com.atguigu.entity.Student;
import com.atguigu.entity.Teacher;
import com.atguigu.mapper.DeptMapper2;
import com.atguigu.mapper.DogMapper2;
import com.atguigu.mapper.StudentMapper2;
import com.atguigu.mapper.TeacherMapper2;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.test
 * @CLASSNAME: OneToMoreRMTest
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/29 20:41
 * @SINCE 17.0.7
 * @DESCRIPTION: 一对多映射 测试
 */
@SpringJUnitConfig(locations = {"classpath:spring-persist.xml"})
public class OneToMoreRMTest {

    @Resource
    private DogMapper2 dogMapper2;
    @Resource
    private DeptMapper2 deptMapper2;

    @Resource
    private TeacherMapper2 teacherMapper2;

    @Resource
    private StudentMapper2 studentMapper2;

    @Test
    public void findAllDept() {
        List<Dept> allDept = deptMapper2.findAllDept();
        allDept.forEach(System.out::println);
    }

    @Test
    public void findAllDog() {
        List<Dog> allDog = dogMapper2.findAllDog();
        allDog.forEach(System.out::println);
    }

    @Test
    public void findAllDogRM() {
        List<Dog> allDogRM = dogMapper2.findAllDogRM();
        allDogRM.forEach(System.out::println);
    }

    @Test
    public void findDogOneToOne1() {
        List<Dog> dogOneToOne1 = dogMapper2.findDogOneToOne_1();
        dogOneToOne1.forEach(System.out::println);
    }

    @Test
    public void findDogOneToOne2() {
        List<Dog> dogOneToOne2 = dogMapper2.findDogOneToOne_2();
        dogOneToOne2.forEach(System.out::println);
    }

    @Test
    public void findAllDept_1() {
        List<Dept> allDept1 = deptMapper2.findAllDept_1();
        deptMapper2.findAllDept_1();
        deptMapper2.findAllDept_1();

        Dog dog = new Dog(null, "土狗", 3, 3);
        int i = dogMapper2.saveDog(dog);
        deptMapper2.findAllDept_1();
        deptMapper2.findAllDept_1();

        //allDept1.forEach(System.out::println);
    }

    @Test
    public void findAllDept_2() {
        List<Dept> allDept2 = deptMapper2.findAllDept_2();
        allDept2.forEach(System.out::println);
    }

    @Test
    public void findTeacherALL_1() {
        List<Teacher> teacherAll1 = teacherMapper2.findTeacherAll_1();
        teacherMapper2.findTeacherAll_1();
        teacherMapper2.findTeacherAll_1();
        teacherMapper2.findTeacherAll_1();
        //teacherAll1.forEach(System.out::println);
    }

    @Test
    public void findTeacherAll_2() {
        List<Teacher> teacherAll2 = teacherMapper2.findTeacherAll_2();
        teacherAll2.forEach(System.out::println);
    }

    @Test
    public void findStudentAll_1() {
        List<Student> allStudent1 = studentMapper2.findAllStudent_1();
        allStudent1.forEach(System.out::println);
    }

    @Test
    public void findStudentAll_2() {
        List<Student> allStudent2 = studentMapper2.findAllStudent_2();
        studentMapper2.findAllStudent_2();
        studentMapper2.findAllStudent_2();
        studentMapper2.findAllStudent_2();
        allStudent2.forEach(System.out::println);
    }

    @Test
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void testOneLevelCache() {
        List<Student> allStudent2 = studentMapper2.findAllStudent_2();
        studentMapper2.findAllStudent_2();
        studentMapper2.findAllStudent_2();
        studentMapper2.findAllStudent_2();
    }
}
