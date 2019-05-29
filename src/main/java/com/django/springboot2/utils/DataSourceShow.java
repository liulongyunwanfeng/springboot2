package com.django.springboot2.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author liulongyun
 * @create 2019/5/29 13:47
 **/
@Component
public class DataSourceShow implements ApplicationContextAware {

    public static  ApplicationContext applicationContext =null;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {

        applicationContext = context;
        DataSource datasource = context.getBean(DataSource.class);
        System.out.println("-------------------------------");
        System.out.println(datasource.getClass().getName());
        System.out.println("-------------------------------");

    }
}
