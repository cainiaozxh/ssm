package com.atguigu.service.impl;

import com.atguigu.mapper.MovieDAO;
import com.atguigu.entity.Movie;
import com.atguigu.service.MovieService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.service.impl
 * @CLASSNAME: MovieServiceImpl
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/27 11:05
 * @SINCE 17.0.7
 * @DESCRIPTION: MovieServiceImpl
 */
@Service
public class MovieServiceImpl implements MovieService {
    @Resource
    private MovieDAO movieDAO;

    @Override
    public List<Movie> getAll() {
        List<Movie> movies = movieDAO.getAllMovies();
        return movies;
    }

    @Override
    public Movie getMovieById(String movieId) {
        Movie movie = movieDAO.getMovieById(movieId);
        return movie;
    }

    @Override
    public boolean saveMovie(Movie movie) {
        boolean b = movieDAO.saveMovie(movie);
        return b;
    }

    @Override
    public boolean updateMovie(Movie movie) {
        boolean b = movieDAO.updateMovie(movie);
        return b;

    }

    @Override
    public boolean removeMovieById(String movieId) {
        boolean b = movieDAO.removeMovieById(movieId);
        return b;
    }
}
