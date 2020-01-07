package com.django.springboot2;

import com.django.springboot2.confirguetion.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

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
 * @ImportResource 用于导入xml配置t
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
@Import({ServiceBeanConfig.class,SpringMvcConfig.class,SSLConfiguration.class,
        MybatisPlusConfig.class, JdbcSecurityConfigure.class})
@PropertySource(value = {"classpath:config/programconfig.properties",
        "classpath:config/serverconfig.properties",
        "classpath:config/httpencoding.properties"},
        encoding = "UTF-8")
@EnableCaching
@EnableAspectJAutoProxy
public class Application {

    private  Logger logger = LoggerFactory.getLogger(this.getClass().getName());
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

    @Autowired
    private PlatformTransactionManager transactionManager = null;



    @PostConstruct
    public void viewTranscationManager(){
        logger.info("===============transactionManager=============:"+transactionManager.getClass().getName());
    }

    /**
     * springboot使用redis
     * 在application.properties中做了redis配置就可以使用RedisTemplate进行redis操作 了
     * 这里主要是重新把默认的序列化方式修改为StringSerializer
     */

    @Autowired
    private RedisTemplate redisTemplate = null;


    @PostConstruct
    public void setRedisTemplateConfig(){

        RedisSerializer stringSerializer = this.redisTemplate.getStringSerializer();
        // 配置序列化方式
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);


    }


}
