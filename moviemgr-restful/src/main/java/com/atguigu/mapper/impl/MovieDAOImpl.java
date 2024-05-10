package com.atguigu.mapper.impl;

import com.atguigu.mapper.MovieDAO;
import com.atguigu.entity.Movie;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.dao.impl
 * @CLASSNAME: MovieDAOImpl
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/27 11:06
 * @SINCE 17.0.7
 * @DESCRIPTION: MovieDAOImpl
 */
@Repository
public class MovieDAOImpl implements MovieDAO {
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取所有的电影
     *
     * @return
     */
    @Override
    public List<Movie> getAllMovies() {
        String sql = "SELECT movie_id movieId, movie_name movieName, movie_price moviePrice FROM movie";
        BeanPropertyRowMapper<Movie> movieBeanPropertyRowMapper = new BeanPropertyRowMapper<>(Movie.class);
        List<Movie> movies = jdbcTemplate.query(sql, movieBeanPropertyRowMapper);
        return movies;
    }

    /**
     * 通过movieId查询指定电影
     *
     * @param movieId
     * @return
     */
    @Override
    public Movie getMovieById(String movieId) {
        String sql = "SELECT movie_id movieId, movie_name movieName, movie_price moviePrice FROM movie WHERE movie_id = ?";
        BeanPropertyRowMapper<Movie> movieBeanPropertyRowMapper = new BeanPropertyRowMapper<>(Movie.class);
        Movie movie = jdbcTemplate.queryForObject(sql, movieBeanPropertyRowMapper, movieId);
        return movie;
    }

    /**
     * 保存movie对象
     *
     * @param movie
     * @return
     */
    @Override
    public boolean saveMovie(Movie movie) {
        String sql = "INSERT INTO movie(movie_id,movie_name,movie_price) VALUES(?, ?, ?)";
        int update = jdbcTemplate.update(sql, movie.getMovieId(), movie.getMovieName(), movie.getMoviePrice());
        return update > 0;
    }

    /**
     * 修改movie
     *
     * @param movie
     * @return
     */
    @Override
    public boolean updateMovie(Movie movie) {
        String sql = "UPDATE movie SET movie_name = ?,movie_price = ? WHERE movie_id = ?";
        int update = jdbcTemplate.update(sql, movie.getMovieName(), movie.getMoviePrice(), movie.getMovieId());
        return update > 0;
    }

    /**
     * 删除指定的movie
     * @param movieId
     * @return
     */
    @Override
    public boolean removeMovieById(String movieId) {
        String sql = "DELETE FROM movie WHERE movie_id = ?";
        int update = jdbcTemplate.update(sql, movieId);
        return update > 0;
    }
}
