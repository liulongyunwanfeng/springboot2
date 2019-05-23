package com.django.springboot2;

import com.django.springboot2.confirguetion.SSLConfiguration;
import com.django.springboot2.confirguetion.ServiceBeanConfig;
import com.django.springboot2.confirguetion.SpringMvcConfig;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.*;
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
 *
 */
@RestController
@SpringBootApplication
@Import({ServiceBeanConfig.class,SpringMvcConfig.class,SSLConfiguration.class})
@PropertySource(value = {"classpath:config/programconfig.properties",
        "classpath:config/serverconfig.properties",
        "classpath:config/httpencoding.properties"},
        encoding = "UTF-8")
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
