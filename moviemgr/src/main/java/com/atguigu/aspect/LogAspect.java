package com.atguigu.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;
import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.aspect
 * @CLASSNAME: LogAspect
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/28 15:19
 * @SINCE 17.0.7
 * @DESCRIPTION: LogAspect 日志切面
 * 在springmvc中日志切面不生效,之后看看啥问题
 */
//@Aspect
//@Component
public class LogAspect {

    private String methodName;

    //@Pointcut("execution(* com.atguigu.dao.MovieDAO.saveMovie(..))")
    public void pointCut() {
    }

    @Before("execution(* com.atguigu.dao.MovieDAO.saveMovie(..))")
    public void printLogBeforeCore(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        Object[] methodArgs = joinPoint.getArgs();
        List<Object> argList = Arrays.asList(methodArgs);
        System.out.println("[AOP切面-1]-[前置通知]: " + methodName + " 方法开始执行了,参数是: " + argList);
    }

    @AfterReturning(value = "execution(* com.atguigu.dao.MovieDAO.saveMovie(..))", returning = "returnValue")
    public void printLogAfterReturning(JoinPoint joinPoint, Object returnValue) {
        Signature signature = joinPoint.getSignature();
        methodName = signature.getName();
        System.out.println("[AOP切面-1]-[返回通知]: " + methodName + " 方法成功返回了,结果是: " + returnValue);
    }

    @AfterThrowing(value = "execution(* com.atguigu.dao.MovieDAO.saveMovie(..))", throwing = "throwable")
    public void printLogAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        Signature signature = joinPoint.getSignature();
        methodName = signature.getName();
        System.out.println("[AOP切面-1]-[异常通知]: " + methodName + " 方法抛出异常了,异常类型是: " +
                throwable.getCause() + ",异常原因是: " + throwable.getMessage());
    }

    @After("execution(* com.atguigu.dao.MovieDAO.saveMovie(..))")
    public void pringLogFinal(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        methodName = signature.getName();
        System.out.println("[AOP切面-1]-[最终通知]: " + methodName + " 最终执行结束了...");
    }
}
