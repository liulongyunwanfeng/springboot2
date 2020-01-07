package com.django.springboot2.confirguetion;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author liulongyun
 * @create 2019/6/8 21:50
 * spirngboot 集成mybatis-plus 配置分页插件
 *
 *
 **/

//Spring boot方式
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages ="com.django.springboot2.dao.mapper" , annotationClass = Repository.class)
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {

        return new PaginationInterceptor();
    }
}
