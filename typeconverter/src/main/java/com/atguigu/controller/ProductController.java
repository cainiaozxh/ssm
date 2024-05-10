package com.atguigu.controller;

import com.atguigu.entity.Product;
import com.atguigu.entity.Product02;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @PACKAGE_NAME: com.atguigu.controller
 * @CLASSNAME: ProductController
 * @AUTHOR: zhangsan
 * @DATE: 2024/2/9 18:58
 * @SINCE 17.0.7
 * @DESCRIPTION: ProductController
 */
@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @RequestMapping("/save")
    public String save(@Validated Product product123, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        log.debug("product = {}",product123);
        return "result";
    }

    @RequestMapping("/save2")
    public String save2(@Validated Product02 product02, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        log.debug("product = {}",product02);
        return "result";
    }
}
