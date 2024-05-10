package com.atguigu.controller;

import com.atguigu.entity.Movie;
import com.atguigu.global.Result;
import com.atguigu.service.MovieService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.atguigu.controller
 * @CLASSNAME: ResultController
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/29 11:12
 * @SINCE 17.0.7
 * @DESCRIPTION: ResultController
 */
@Controller
@Validated
@RequestMapping("/result")
public class ResultController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MovieService movieService;

    @PostMapping("/add")
    public Result add(@Validated Movie movie,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> map = new HashMap<>();
            bindingResult.getFieldErrors().forEach(fieldError -> {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
            return Result.failed(400, "提交的数据不合规范", map);
        }
        boolean b = movieService.saveMovie(movie);
        if (b) {
            logger.debug("新增成功");
        }
        return Result.success(b);
    }

}
