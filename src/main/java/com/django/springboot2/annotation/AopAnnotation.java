package com.django.springboot2.annotation;

import java.lang.annotation.*;

/**
 * @author liulongyun
 * @create 2019/6/8 12:44
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AopAnnotation {
    String desc();

}
