package com.atguigu.ssm.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.atguigu.ssm.entity.Employee;
import com.atguigu.ssm.mapper.EmployeeMapper;
import com.atguigu.ssm.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.ssm.test
 * @CLASSNAME: SSMTest
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/30 22:10
 * @SINCE 17.0.7
 * @DESCRIPTION: SSMTest
 */
@SpringJUnitConfig(locations = {"classpath:spring-persist.xml"})
public class SSMTest {

    @Resource
    private DruidDataSource druidDataSource;

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private EmployeeService employeeService;

    @Test
    public void getConnection() {
        DruidPooledConnection connection = null;
        try {
            connection = druidDataSource.getConnection();
            System.out.println("connection = " + connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void testFindAll() {
        List<Employee> allEmployee = employeeMapper.findAllEmployee();
        allEmployee.forEach(System.out::println);
    }

    @Test
    public void testSave() {
        Employee employee = new Employee(null, "张三", 100.0);
        int i = employeeMapper.saveEmp(employee);
        System.out.println("i = " + i);
    }

    @Test
    public void testSaveTx() {
        Employee employee = new Employee(null, "tom1", 10.0);
        Employee employee1 = new Employee(null, "jerry1", 11.0);
        int i = employeeService.saveEmpTx(employee, employee1);
        System.out.println("i = " + i);
    }

    @Test
    public void testUpdate() {
        Employee employee = new Employee(72, "james", 2.0);
        int i = employeeMapper.updateEmp(employee);
        System.out.println("i = " + i);
    }
}
