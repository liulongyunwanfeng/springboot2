package com.django.springboot2;

import com.django.springboot2.confirguetion.SSLConfiguration;
import com.django.springboot2.confirguetion.ServiceBeanConfig;
import com.django.springboot2.confirguetion.SpringMvcConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Application
 * @Author Administrator
 * @Date 2019/5/9
 * @Version 1.0
 * @Description TODO
 * @SpringBootApplication 是一个组合注解
 * 它组合了如下三个注解
 *          @SpringBootConfiguration
*           @EnableAutoConfiguration
 *          @ComponentScan
 *
 * @Import 用于导入java配置
 * @ImportResource 用于导入xml配置
 *
 * @MapperScan
 *  @MapperScan允许我们通过扫描加载mybatis的Mapper,如果springboot中不存在多个SqlSessionFactory
 *  或者SqlSessionTemplate，那么完全可以不配置sqlSessionFactoryFef(或者SqlSessionTemplateRef)
 *  所以这里没有配置这些属性，basePackages 配置mybatis相关注解的扫描包，并结合@Repostory注解作为数据库实体限定
 *  也可以用@Mapper(@Repostory是spring对持久层的注解，@Mapper是mybatis提供的持久层注解)
 *
 */
@RestController
@SpringBootApplication
@Import({ServiceBeanConfig.class,SpringMvcConfig.class,SSLConfiguration.class})
@PropertySource(value = {"classpath:config/programconfig.properties",
        "classpath:config/serverconfig.properties",
        "classpath:config/httpencoding.properties"},
        encoding = "UTF-8")
@MapperScan(basePackages ="com.django.springboot2" , annotationClass = Repository.class)
public class Application {

    public static void main(String[] args) {

        // SpringApplication.run(Application.class, args);

//        SpringApplication application = new SpringApplication(Application.class);
//        application.run(args);

        // 使用 SpringApplicationBuilder 启动项目
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        //修改Banner的模式为OFF
        //        builder.bannerMode(Banner.Mode.OFF).run(args);

        builder.run(args);

    }




}
