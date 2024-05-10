package com.atguigu.controller;

import com.atguigu.entity.Movie;
import com.atguigu.service.MovieService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.controller
 * @CLASSNAME: MovieController
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/27 15:51
 * @SINCE 17.0.7
 * @DESCRIPTION: MovieController
 */
@Slf4j
@Controller
@RequestMapping("/movie")
public class MovieController {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Resource
    private MovieService movieService;

    @GetMapping("/getAll")
    public String getAllMovies(Model model) {
        List<Movie> movieList = movieService.getAll();
        model.addAttribute("movieList", movieList);
        return "movie";
    }

    @DeleteMapping("/delete/{movieId}")
    public String deleteMovieById(
            @PathVariable("movieId") String movieId) {
        boolean b = movieService.removeMovieById(movieId);
        if (b) {
            return "redirect:/movie/getAll";
        } else {
            throw new RuntimeException("删除失败");
        }
    }

    @PostMapping("/addMovie")
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
     * 你好啊,兄弟
     *
     * @param movieId
     * @param model
     * @return
     */
    @GetMapping("/getMovie/{movieId}")
    public String getMovieById(
            @PathVariable("movieId") String movieId,
            Model model) {
        Movie orinigalMovie = movieService.getMovieById(movieId);
        model.addAttribute("originalMovie", orinigalMovie);
        return "movie_update";
    }

    @PutMapping("/update")
    public String updateMovie(Movie movie) {
        boolean b = movieService.updateMovie(movie);
        if (b) {
            return "redirect:/movie/getAll";
        } else {
            throw new RuntimeException("修改失败");
        }
    }

    @RequestMapping("/getMovieJson")
    public void getMoiveJson(@RequestBody String movieId,
                             HttpServletResponse resp) {
        log.debug("movieId = " + movieId);
        resp.setContentType("application/json;charset=utf-8");
        String respBody = "";

        Movie movie = movieService.getMovieById(movieId);
        try {
            respBody = objectMapper.writeValueAsString(movie);
            PrintWriter writer = resp.getWriter();
            writer.write(respBody);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/getMovieJson2")
    public void getMoiveJson2(HttpServletRequest req,
                              HttpServletResponse resp) {
        resp.setContentType("application/json;charset=utf-8");
        String readline = "";
        String requestBody = "";
        String respBody = "";
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader reader = req.getReader();
            while ((readline = reader.readLine()) != null) {
                sb.append(readline);
            }
            requestBody = sb.toString();

            log.debug("requestBody = " + requestBody);

            //将前端发送的数据转换为对象
            TypeReference<Movie> movieTypeReference = new TypeReference<Movie>() {
            };
            Movie aMovie = objectMapper.readValue(requestBody, movieTypeReference);

            Movie movie = movieService.getMovieById(aMovie.getMovieId());
            respBody = objectMapper.writeValueAsString(movie);
            PrintWriter writer = resp.getWriter();
            writer.write(respBody);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
