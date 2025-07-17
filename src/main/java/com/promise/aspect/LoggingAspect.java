package com.promise.aspect;

import com.promise.annotation.CustomLogging;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ClassName LoggingAspect
 * @Description TODO
 * @Author lizh-w
 * @Date 2025/2/20 15:14
 * @Version 1.0
 */
@Aspect
@Component
public class LoggingAspect {
    // Pointcut定义：匹配com.promise.service.Impl包下所有类的所有方法
//    @Before("execution(* com.promise.service.Impl..*(..))")
//    public void logBefore() {
//        System.out.println("Method is about to be called.");
//    }
//
//    @After("execution(* com.promise.service.Impl..*(..))")
//    public void logAfter() {
//        System.out.println("Method has been called.");
//    }
    @Pointcut("@annotation(com.promise.annotation.CustomLogging)")
    public void logging() {
    }

    @Before("logging()")
    public void beforeCalled(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("---------  " + method + "  ------------will be call.");

        // 获取注解
        CustomLogging annotation = method.getAnnotation(CustomLogging.class);
        if (annotation != null) {
            System.out.println(annotation.customValue());

        }

    }

    @Before("logging()")
    public void afterCalled(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("---------  " + method + "  ------------has been called.");

        // 获取注解
        CustomLogging annotation = method.getAnnotation(CustomLogging.class);
        if (annotation != null) {
            System.out.println(annotation.customValue());

        }

    }

}
