package com.atguigu.service;

import com.atguigu.entity.Movie;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.service
 * @CLASSNAME: MovieService
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/27 11:04
 * @SINCE 17.0.7
 * @DESCRIPTION: MovieService
 */
public interface MovieService {

    List<Movie> getAll();

    Movie getMovieById(String movieId);

    boolean saveMovie(Movie movie);

    boolean updateMovie(Movie movie);


    boolean removeMovieById(String movieId);

}
