package com.django.springboot2.confirguetion;

import com.django.springboot2.pojo.vo.MyMessageConverter;
import com.django.springboot2.web.inteceptor.DemoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * @ClassName SpringMvcConfig
 * @Author Administrator
 * @Date 2019/5/9
 * @Version 1.0
 * @Description TODO
 *
 * @EnableWebMvc
 * 如果想用自己的配置替代springboot的配置，则在自己的配置上加上@EnableWebMvc
 * 如果是想在springboot的默认配置的基础上扩展自己的配置，则不需要加@EnableWebMvc这个注解
 *
 *
 *
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    /**
     * @Author django
     * @Date   2019/5/10
     * @param   * @param
     * @return org.springframework.web.servlet.view.InternalResourceViewResolver
     * @Desc   jsp模板配置
     * springboot默认使用Themeleaf作为试图模板，如果使用jsp作为试图模板，需要配置。
    */
//    @Bean
//    public InternalResourceViewResolver viewResolver(){
//
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/classes/views");
//        viewResolver.setSuffix(".jsp");
//        viewResolver.setViewClass(JstlView.class);
//        return viewResolver;
//    }

    @Bean
    public DemoInterceptor demoInterceptor(){
        return  new DemoInterceptor();
    }


    /**
     * @Author django
     * @Date   2019/5/7
     * @param   * @param registry
     * @return void
     * @Desc   addResourceLocations 指定文件放置的目录，addResourceHandler 指定对外暴露的访问路径
     *
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/page/**").addResourceLocations("classpath:/templates/");
    }

    /**
     * @Author django
     * @Date   2019/5/7
     * @param   * @param registry
     * @return void
     * @Desc   注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(demoInterceptor());
    }


    /**
     * @Author django
     * @Date   2019/5/7
     * @param   * @param registry
     * @return void
     * @Desc   重写这个可以配置试图，比如简单的页面试图跳转可以直接在这里配置
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        /**
         * @Author django
         * @Date   2019/5/7
         * @param   * @param registry
         * @return void
         * @Desc   这样还设置了默认页
         * 这里的配置不会覆盖WebMvcAutoConfiguration的自动配置，将同时生效
         */
        registry.addViewController("/").setViewName("/index");

    }



    @Bean
    public MultipartResolver multipartResolver(){

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        // 设置默认编码，已解决文件中文名乱码的问题
        multipartResolver.setDefaultEncoding("UTF-8");
        multipartResolver.setMaxUploadSize(50*1024);
        return multipartResolver;

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }

    @Bean
    public MyMessageConverter converter(){
        return new MyMessageConverter();
    }
}
