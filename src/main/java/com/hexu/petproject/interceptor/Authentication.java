package com.hexu.petproject.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 86176
 * 自定义注解   用来做权限认证   方法上有这个注解  就说明不需要验证  课直接放行
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authentication {
    String authorize() default "";
}
