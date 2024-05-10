package com.atguigu.test;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.atguigu.entity.Employee;
import com.atguigu.mapper.EmployeeMapper;
import com.atguigu.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Consumer;

/**
 * @PACKAGE_NAME: com.atguigu.test
 * @CLASSNAME: SSMTest
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/26 11:34
 * @SINCE 17.0.7
 * @DESCRIPTION: SSMTest
 */
@SpringJUnitConfig(locations = "classpath:spring-persist.xml")
public class SSMTest {

    @Resource
    private DruidDataSource druidDataSource;
    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private EmployeeService employeeService;

    @Test
    public void testConnect() {
        Connection connection = null;
        try {
            connection = druidDataSource.getConnection();
            System.out.println(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Test
    public void testFindAll() {
        List<Employee> employees = employeeMapper.findAll();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void testInsert() {
        Employee employee = new Employee(null, "谢逊", 123.0);
        int i = employeeMapper.saveEmp(employee);
        System.out.println("i = " + i);
    }

    @Test
    public void testSuperIOC() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("classpath:spring-persist.xml");
        int count = ioc.getBeanDefinitionCount();
        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
        System.out.println("count = " + count);
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    @Test
    public void testSubIOC() {
        ApplicationContext subIOC = new ClassPathXmlApplicationContext("classpath:spring-mvc.xml");
        int count = subIOC.getBeanDefinitionCount();
        System.out.println("count = " + count);
        String[] beanDefinitionNames = subIOC.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

    @Test
    public void testService() {
        Employee employee = new Employee(null, "悍匪", 100.0);
        int i = employeeService.saveEmployee(employee);
        System.out.println("i = " + i);
    }

    @Test
    public void testService2() {
        List<Employee> employees = employeeService.findAll();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void testTX() {
        Employee employee = new Employee(null, "lisi", 123.0);
        Employee employee1 = new Employee(null, "lisiwwwwwwwwwwwwwwwww", 123.0);
        employeeService.saveEmployee2(employee, employee1);
    }

    @Test
    public void testZWF() {
        Employee employee = employeeService.selectEmployeeByName("jackson");
        System.out.println("employee = " + employee);
    }

    @Test
    public void testOrder() {
        List<Employee> orderedEmployees = employeeMapper.orderEmployeeByColumn("emp_salary");
        for (Employee orderedEmployee : orderedEmployees) {
            System.out.println(orderedEmployee);
        }
    }

    @Test
    public void testMap() {
        String empName = "jack";
        double empSalary = 1000.0;
        Map<String, Object> map = new HashMap<>(2);
        map.put("empName", empName);
        map.put("empSalary", empSalary);
        Employee employee = employeeMapper.selectEmployeeByMap(map);
        System.out.println("employee = " + employee);
    }

    @Test
    public void testAnno() {
        String empName = "jack";
        double empSalary = 1000.0;

        Employee employee = employeeMapper.selectEmployeeByAnno(empName, empSalary);
        System.out.println("employee = " + employee);
    }

    @Test
    public void testEntity() {
        String empName = "jack";
        double empSalary = 1000.0;

        Employee employee = new Employee(null, empName, empSalary);
        List<Employee> employees = employeeMapper.selectEmployeeByEntity(employee);
        for (Employee employee1 : employees) {
            System.out.println(employee1);
        }
    }

    @Test
    public void testSelectById() {
        Integer empId = 2;
        Employee employee = employeeMapper.selectEmployeeById(empId);
        System.out.println("employee = " + employee);
    }

    /**
     * 动态获取主键信息
     */
    @Test
    public void testSaveGetId() {
        Employee employee = new Employee(null, "lili", 1000.0);
        System.out.println("employee1 = " + employee);
        int i = employeeMapper.saveEmpGetId(employee);
        System.out.println("i = " + i);
        System.out.println("employee2 = " + employee);
    }

    @Test
    public void updateEmployee() {
        Employee employee = new Employee(64, "XPENG", 10.0);
        int i = employeeMapper.updateEmployee(employee);
        System.out.println("employee1 = " + employee);
        System.out.println("i = " + i);
        Employee employee2 = employeeMapper.selectEmployeeById(employee.getEmpId());
        System.out.println("employee2 = " + employee2);
    }

    /**
     * 删除用户
     */
    @Test
    public void deleteEmployee() {
        Integer empId = 64;
        int i = employeeMapper.deleteEmployeeById(empId);
        System.out.println("i = " + i);
        Employee employee = employeeMapper.selectEmployeeById(empId);
        System.out.println("employee = " + employee);
    }

    @Test
    public void testSelectEmployee() {
        double minSalary = 100.0;
        double maxSalary = 1000.0;
        List<Employee> employees = employeeMapper.selectEmpBySalary(minSalary, maxSalary);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        System.out.println("========1========");

        List<Employee> employees1 = employeeMapper.selectEmpBySalary2(minSalary, maxSalary);
        employees1.forEach(System.out::println);

        System.out.println("========2========");

        List<Employee> employees2 = employeeMapper.selectEmpBySalary3(minSalary, maxSalary);
        employees2.forEach(System.out::println);
    }

    @Test
    public void testSelectById2() {
        Integer empId = 65;
        Employee employee = employeeMapper.selectEmployeeById(empId);
        System.out.println("employee = " + employee);
    }

    /**
     * 查询某些字段信息,用map封装
     */
    @Test
    public void selectEmployeeMap() {
        Integer empId = 65;
        Map<String, Object> map = employeeMapper.selectEmployeeMap(empId);
        map.forEach((key, value) -> {
            System.out.println(key + " = " + value);
        });
    }

    @Test
    public void selectEmployeeMap2() {
        Integer empId = 1;
        List<Map<String, Object>> maps = employeeMapper.selectEmployeeMapNameSalary(empId);
        maps.forEach(stringObjectMap -> System.out.println(stringObjectMap));
    }

    @Test
    public void selectEmployeeMapMap() {
        Integer empId = 62;
        Map<Integer, Map<String, Object>> integerMapMap = employeeMapper.selectEmployeeMapMap(empId);
        integerMapMap.forEach((Integer key, Map<String, Object> valueMap) -> {
            System.out.println(key + " = " + valueMap);
        });
    }

    @Test
    public void selectEmployeeByLike() {
        List<Employee> employees = employeeMapper.selectEmployeeByLike("ac");
        employees.forEach(System.out::println);
    }

    @Test
    public void selectEmployees() {
        Integer[] array = {2, 5, 42, 46, 47, 48, 62};
        List<Employee> employees = employeeMapper.selectEmployees(array);
        employees.forEach(System.out::println);
        System.out.println("========1=======");
    }

    @Test
    public void selectEmployeeList() {
        Integer[] array = {2, 5, 42, 46, 47, 48, 62};
        List<Integer> list = Arrays.asList(array);
        List<Employee> employees1 = employeeMapper.selectEmployeesList(list);
        employees1.forEach(System.out::println);
    }

    @Test
    public void selectEmployeesMap() {
        Integer[] array = {2, 5, 42, 46, 47, 48, 62};
        List<Integer> list = Arrays.asList(array);

        Map<String,List<Integer>> map = new HashMap<>();
        map.put("ids", list);
        List<Employee> employees2 = employeeMapper.selectEmployeesMap(map);
        employees2.forEach(System.out::println);
    }

    @Test
    public void testAsList() {
        Integer[] array = {2, 5, 42, 46, 47, 48, 62};
        List<Integer> list = Arrays.asList(array);
    }

    @Test
    public void selectEmployeeWhere() {
        Employee employee = new Employee(null, "jack", null);
        List<Employee> employees = employeeMapper.selectEmployeeByWhere(employee);
        employees.forEach(System.out::println);

        System.out.println("=======1========");

        Employee employee1 = new Employee(64, null, null);
        List<Employee> employees1 = employeeMapper.selectEmployeeByWhere(employee1);
        employees1.forEach(System.out::println);

        System.out.println("=======2========");
        Employee employee2 = new Employee(null, null, 1000.0);
        List<Employee> employees2 = employeeMapper.selectEmployeeByWhere(employee2);
        employees2.forEach(System.out::println);
    }

    /**
     * 动态修改操作:
     * 根据对象中不为null的数据充当set条件,ID作为唯一的where条件
     */
    @Test
    public void updateEmployeeBySet() {
        Employee employee = new Employee(65, "tomcat", 100.0);
        int i = employeeMapper.updateEmployeeBySet(employee);
        System.out.println("i = " + i);
        Employee employee1 = employeeMapper.selectEmployeeById(employee.getEmpId());
        System.out.println(employee1);
    }

    /**
     * 需求介绍:
     * 根据用户的条件查询数据
     * 规则:如果用户传递的id号 则可以ID查询
     *      如果用户没有传递id号码,传递了name则可以使用name查询
     *      否则使用age查询
     */
    @Test
    public void testSelectChoose() {
        Employee employee = new Employee(65, null, null);
        List<Employee> employeeList = employeeMapper.selectChoose(employee);
        employeeList.forEach(System.out::println);
        System.out.println("========1=======");

        Employee employee1 = new Employee(null, "jack", null);
        List<Employee> employeeList1 = employeeMapper.selectChoose(employee1);
        employeeList1.forEach(System.out::println);
        System.out.println("========2=======");
        Employee employee2 = new Employee(null, null, 1000.0);
        List<Employee> employeeList2 = employeeMapper.selectChoose(employee2);
        employeeList2.forEach(System.out::println);
    }
}
