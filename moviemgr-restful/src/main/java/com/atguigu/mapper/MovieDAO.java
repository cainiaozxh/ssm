package com.atguigu.mapper;

import com.atguigu.entity.Movie;

import java.util.List;


/**
 * @PACKAGE_NAME: com.atguigu.dao
 * @CLASSNAME: MovieDAO
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/27 11:05
 * @SINCE 17.0.7
 * @DESCRIPTION: MovieDAO
 */
public interface MovieDAO {
    /**
     * 获取所有的movie信息
     * @return
     */
    List<Movie> getAllMovies();

    /**
     * 通过movieId 查询指定movie
     * @param movieId
     * @return
     */
    Movie getMovieById(String movieId);

    /**
     * 保存movie对象
     * @param movie
     * @return
     */
    boolean saveMovie(Movie movie);

    /**
     * 修改movie
     * @param movie
     * @return
     */
    boolean updateMovie(Movie movie);

    /**
     * 删除指定movie
     * @param movieId
     * @return
     */
    boolean removeMovieById(String movieId);
}
