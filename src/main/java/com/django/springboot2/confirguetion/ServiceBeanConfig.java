package com.django.springboot2.confirguetion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @ClassName ServerBeanConfig
 * @Author Administrator
 * @Date 2019/5/9
 * @Version 1.0
 * @Description TODO
 */
@Configuration
public class ServiceBeanConfig {

    @Bean(name = "jsonView")
    public MappingJackson2JsonView jackson2JsonView(){
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        return jsonView;
    }


}
