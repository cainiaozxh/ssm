package com.atguigu.controller;

import com.atguigu.entity.Movie;
import com.atguigu.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.controller
 * @CLASSNAME: MovieController
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/27 15:51
 * @SINCE 17.0.7
 * @DESCRIPTION: MovieController
 */
@Controller
@RequestMapping("/movie")
public class MovieController {

    @Resource
    private MovieService movieService;

    @RequestMapping("/getAll")
    public String getAllMovies(Model model) {
        List<Movie> movieList = movieService.getAll();
        model.addAttribute("movieList", movieList);
        return "movie";
    }
    @RequestMapping("/deleteMovieById")
    public String deleteMovieById(
            @RequestParam("movieId") String movieId) {
        boolean b = movieService.removeMovieById(movieId);
        if (b) {
            return "redirect:/movie/getAll";
        } else {
            throw new RuntimeException("删除失败");
        }
    }

    @RequestMapping("/addMovie")
    public String addMovie(@Validated Movie movie,
                           BindingResult bindingResult) {
        boolean b = movieService.saveMovie(movie);
        if (b) {
            return "redirect:/movie/getAll";
        } else {
            throw new RuntimeException("新增失败");
        }
    }

    /**
     * 回显表单数据
     * @param movieId
     * @param model
     * @return
     */
    @RequestMapping("/getMovieById")
    public String getMovieById(
            @RequestParam("movieId") String movieId,
            Model model) {
        Movie orinigalMovie = movieService.getMovieById(movieId);
        model.addAttribute("originalMovie", orinigalMovie);
        return "movie_update";
    }

    @RequestMapping("/update")
    public String updateMovie(Movie movie) {
        boolean b = movieService.updateMovie(movie);
        if (b) {
            return "redirect:/movie/getAll";
        } else {
            throw new RuntimeException("修改失败");
        }
    }
}
