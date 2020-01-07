package com.django.springboot2.aop;

import com.django.springboot2.controller.FileController;
import com.django.springboot2.controller.UserController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author liulongyun
 * @create 2019/6/8 12:32
 **/
@Aspect
@Component
public class LogAop {

    private Logger logger = LoggerFactory.getLogger(LogAop.class);




    @Pointcut("@annotation(com.django.springboot2.annotation.AopAnnotation)")
    public void annotationPointCut(){}

    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint){
        MethodSignature methodSignature =  (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        logger.info("前置拦截=============:"+method.getName());

    }

//    @After("annotationPointCut()")
//    FileController.uploadRequest
//@Before("execution(* com.django.service.aop.service.CommonDao.*(..))")
    @After("execution(* com.django.springboot2.controller.*.*(..))")
    public void after(JoinPoint joinPoint){
        MethodSignature methodSignature =  (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        logger.info("后置拦截=============:"+method.getName());


        // 在这里记录日志



    }


}
