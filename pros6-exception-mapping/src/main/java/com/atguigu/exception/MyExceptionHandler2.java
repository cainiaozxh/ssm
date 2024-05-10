package com.atguigu.exception;

import com.atguigu.util.MyUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @PACKAGE_NAME: com.atguigu.exception
 * @CLASSNAME: MyExceptionHandler2
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/24 13:30
 * @SINCE 17.0.7
 * @DESCRIPTION: MyExceptionHandler2 异常处理类
 */
@ControllerAdvice//底层就是@Component说明这是一个异常处理类
public class MyExceptionHandler2 {
    @ExceptionHandler(value = NullPointerException.class)
    public String resolveNullPointException(Model model) {
        model.addAttribute("atguiguException", "空指针异常");
        return "exp-null";
    }

    @ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
    public String resolveArrayIndexOutOfBoundsException(Model model) {
        model.addAttribute("atguiguException", "数组索引越界异常");
        return "exp-aiobe";
    }

    @ExceptionHandler(value = ArithmeticException.class)
    public String resolveArithmeticException(Model model) {
        model.addAttribute("atguiguException", "除数不能为零");
        return "exp-arith";
    }

    @ExceptionHandler(value = ClassNotFoundException.class)
    public String resolveClassNotFoundException(HttpServletRequest request,
                                                HttpServletResponse response,
                                                Model model,
                                                Exception e) {
        //判断是不是ajax请求
        PrintWriter writer = null;

        if (MyUtil.judgeRequestType(request)) {
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
            model.addAttribute("atguiguException", e);
            return "exp-run";
        }
    }
}
