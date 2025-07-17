package com.promise.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName CustomLogging
 * @Description TODO
 * @Author lizh-w
 * @Date 2025/2/20 17:55
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomLogging {
    String value();
    String customValue();
}
