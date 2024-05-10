package com.atguigu.exception;

import com.atguigu.util.MyUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @PACKAGE_NAME: com.atguigu.exception
 * @CLASSNAME: MyExceptionHandler
 * @AUTHOR: zhangsan
 * @DATE: 2024/2/10 17:42
 * @SINCE 17.0.7
 * @DESCRIPTION: MyExceptionHandler
 */
//@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = NullPointerException.class)
    public String resolveNullPointException(Model model) {
        model.addAttribute("atguiguException123", "空指针异常...");
        return "exp-null2";
    }

    @ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
    public String resultArrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException e, Model model) {
        model.addAttribute("atguiguException", e);
        return "exp-aiobe";
    }

    @ExceptionHandler(value = ClassNotFoundException.class)
    public String resolveRuntimeException(Exception e, Model model, HttpServletRequest request, HttpServletResponse response) {
        boolean flag = MyUtil.judgeRequestType(request);
        PrintWriter writer = null;
        //是 ajax请求
        if (flag) {
            try {
                writer = response.getWriter();
                writer.print(e);
                writer.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } finally {
                writer.close();
            }
            return null;
        } else {
            //不是ajax请求,则跳转到某个页面
            model.addAttribute("atguiguException", e);
            return "exp-run";
        }

    }
}
