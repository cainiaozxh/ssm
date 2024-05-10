package com.atguigu.redis.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.atguigu.redis.entity.Employee;
import com.atguigu.redis.mapper.EmployeeMapper;
import com.github.pagehelper.PageInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @PACKAGE_NAME: com.atguigu.redis.test
 * @CLASSNAME: BasicTest
 * @AUTHOR: zhangsan
 * @DATE: 2024/4/10 17:42
 * @SINCE 17.0.7
 * @DESCRIPTION: TODO
 */
@SpringJUnitConfig(locations = {"classpath:spring-persist.xml"})
public class BasicTest {
    @Resource
    private DruidDataSource druidDataSource;
    @Resource
    private EmployeeMapper employeeMapper;

    @Test
    public void testConnection() {
        Connection connection = null;
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
    public void findAll() {
        List<Employee> employees = employeeMapper.findAll();
        System.out.println(employees);
    }

    @Test
    public void testRedis() {
        Jedis jedis = new Jedis("192.168.74.200", 6379);
        String ping = jedis.ping();
        System.out.println("ping = " + ping);
        jedis.close();
    }

    @Test
    public void testRegex() {
        // string = bd586dfe-34a1-42f3-9d36-3a109bce8bd9
        // bd586df
        String inputCode = "bd586d";
        String regex = "^[0-9a-zA-Z]{6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputCode);
        if (matcher.find()) {
            System.out.println("找到了");
            String group0 = matcher.group(0);
            //String group1 = matcher.group(1);
            System.out.println("group0 = " + group0);
            //System.out.println("group1 = " + group1);
        } else {
            System.out.println("没找到");
        }
    }

    @Test
    public void verifyPhoneNum() {
        String phoneNum = "18888888888";
        String regex = "^1[3|4|5|8]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNum);
        if (matcher.find()) {
            System.out.println("找到了");
            String group0 = matcher.group(0);
            System.out.println("group0 = " + group0);
        }
    }
}
