import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @PACKAGE_NAME: PACKAGE_NAME
 * @CLASSNAME: TestMovieRestful
 * @AUTHOR: zhangsan
 * @DATE: 2024/2/5 19:37
 * @SINCE 17.0.7
 * @DESCRIPTION: 测试类
 */
@SpringJUnitConfig(locations = {"classpath:spring-mvc.xml"})
public class TestMovieRestful {

    @Resource
    private ApplicationContext ioc;

    @Test
    public void testConnection() {
        Connection connection = null;
        try {
            DruidDataSource druidDataSource = ioc.getBean("druidDataSource", DruidDataSource.class);
            connection = druidDataSource.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
