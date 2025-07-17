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
//        // 移除了测试打印信息
//    }
//
//    @After("execution(* com.promise.service.Impl..*(..))")
//    public void logAfter() {
//        // 移除了测试打印信息
//    }
    @Pointcut("@annotation(com.promise.annotation.CustomLogging)")
    public void logging() {
    }

    @Before("logging()")
    public void beforeCalled(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 移除了测试打印信息

        // 获取注解
        CustomLogging annotation = method.getAnnotation(CustomLogging.class);
        if (annotation != null) {
            // 移除了测试打印信息
        }

    }

    public void logAfter() {
    }

}
