package com.teachingbase.controller;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class MyControllerAdvice {

    /**
     * Shiro注解模式下，登录失败与没有权限都是通过抛出异常。并且默认并没有去处理或者捕获这些异常。
     * 在SpringMVC下需要配置捕获相应异常来通知用户信息
     * 处理UnauthorizedException.class异常
     * @return
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public String auth(){
        return "error/unauthorized";
    }

    @ExceptionHandler(value = Exception.class)
    public String exception(){
        return "error/500";
    }
}
