package com.atguigu.controller;

import com.atguigu.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 除数为0,ArithmeticException
     * @return
     */
    @RequestMapping("/save1")
    public String save1() {
        int i = 10/0;
        return "result";
    }

    /**
     * 空指针异常
     * @return
     */
    @RequestMapping("/save2")
    public String save2() {
        String str = null;
        System.out.println(str.length());
        return "result";
    }

    /**
     * 数组索引越界异常 runtimeException
     * @return
     */
    @RequestMapping("/save3")
    public String save3() {
        int[] arr = new int[5];
        System.out.println(arr[10]);
        return "result";
    }

    /**
     * ClassNotFoundException 属于 Exception
     * @return
     * @throws ClassNotFoundException
     */
    @RequestMapping("/save4")
    public String save4() throws ClassNotFoundException {
        Class.forName("com.abc.def.Spring");
        return "result";
    }

    @RequestMapping("/save5")
    @ResponseBody
    public String save5() throws ClassNotFoundException {
        //Class.forName("com.abc.def.Spring");
        return "ok";
    }

}
