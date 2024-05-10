import com.alibaba.druid.pool.DruidDataSource;
import com.atguigu.mapper.MovieDAO;
import com.atguigu.entity.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @PACKAGE_NAME: PACKAGE_NAME
 * @CLASSNAME: MoviemgrTest
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/27 14:52
 * @SINCE 17.0.7
 * @DESCRIPTION: TODO
 */
@SpringJUnitConfig(locations = {"classpath:spring-mvc.xml"})
public class MoviemgrTest {

    @Resource
    private ApplicationContext ioc;
    @Resource
    private MovieDAO movieDAO;

    @Test
    public void testDataSource() {
        Connection connection = null;
        try {
            //ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-mvc.xml");
            DruidDataSource dataSource = ioc.getBean("druidDataSource", DruidDataSource.class);
            connection = dataSource.getConnection();
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

    @Test
    public void testGetMovieById() {
        Movie movie = movieDAO.getMovieById("m01");
        System.out.println(movie);
    }
}
