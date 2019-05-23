package com.django.springboot2.confirguetion;

import com.django.springboot2.pojo.bo.HttpEncodingProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @ClassName HttpEncodingAutoConfiguration
 * @Author Administrator
 * @Date 2019/5/10
 * @Version 1.0
 * @Description
 * 配置http请求编码过滤器
 * 在使用web.xml的时候我们配置编码过滤器使用的配置：
 *
 * 	<filter>
 * 		<filter-name>characterEncodingFilter</filter-name>
 * 		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
 * 		<init-param>
 * 			<param-name>encoding</param-name>
 * 			<param-value>UTF-8</param-value>
 * 		</init-param>
 * 		<init-param>
 * 			<param-name>forcencoding</param-name>
 * 			<param-value>true</param-value>
 * 		</init-param>
 * 	</filter>
 * 	<filter-mapping>
 * 		<filter-name>characterEncodingFilter</filter-name>
 *      <url-pattern>/*</url-pattern>
 * 	</filter-mapping>
 *
 * 现在我们让springboot自动为我们配置CharacterEncodingFilter 这个bean,来实现编码
 * 这个自动配置还要满足连个条件
 * 1：能配置CharacterEncodingFilter 这个bean
 * 2：能配置encoding和forceEncoding这两个参数
 * 我们这样：
 * 在httpencoding.properties文件中做属性配置：
 * spring.http.encoding.chartset=UTF-8
 * spring.http.encoding.force=true
 * 将这个配置板顶到一个Java实例上（HttpEncodingProperties）
 * 在配置文件中注入这个实例：
 *    @Autowired
 *     private HttpEncodingProperties httpEncodingProperties;
 *  在配置文件中创建过滤器CharacterEncodingFilter，设置属性
 *
 *          CharacterEncodingFilter filter = new CharacterEncodingFilter();
 *         filter.setEncoding(this.httpEncodingProperties.getCharset().name());
 *         filter.setForceEncoding(this.httpEncodingProperties.isForce());
 *         return filter;
 *
 *  以下是所用到的相关注解的说明
 *  @EnableConfigurationProperties
 *
 *      该注解是用来开启对@ConfigurationProperties注解配置Bean的支持。
 *      也就是@EnableConfigurationProperties注解告诉Spring Boot
 *      能支持@ConfigurationProperties。如果不指定会看到如下异常:
 *
 *    当然，也可以不用@EnableConfigurationProperties 这个注解.
 *
 *    而配合@Componnet 注解和@ConfigurationProperties使用:
 *    @Component
 *    @ConfigurationProperties(prefix = "spring.http.encoding")
 *    public class HttpEncodingProperties {
 *      }
 *      用了@EnableConfigurationProperties  这个注解就不用在对于类上加@Componnet 注解了
 *
 *    @ConditionalOnClass
 *      条件注解：在classpath路径有对应的类时条件成立的意思
 *
 *    @ConditionalOnProperty
 *      当配置文件设置了spring.http.encoding=enabled的情况下成立，如果没有设置则默认为true,即条件符合
 *
 *     @ConditionalOnMissingBean
 *      当容器里没有对应的bean时条件成立
 *      （这里的意思就是当容器里没有CharacterEncodingFilter 这个bean是创建这个bean）
 *
 *      这个配置springboot本身就做了的，这为了学习自己写一个而已
 *      也就是说springboot已经帮我们做好了编码过滤器的设置
 *      在这里
 *      org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
@Configuration
@EnableConfigurationProperties(HttpEncodingProperties.class)
@ConditionalOnClass(CharacterEncodingFilter.class)
@ConditionalOnProperty(prefix = "spring.http.enable",value = "enable",matchIfMissing = true)
public class HttpEncodingAutoConfiguration {

    @Autowired
    private HttpEncodingProperties httpEncodingProperties;

    @Bean
    @ConditionalOnMissingBean(CharacterEncodingFilter.class)
    public CharacterEncodingFilter characterEncodingFilter(){
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding(this.httpEncodingProperties.getCharset().name());
        filter.setForceEncoding(this.httpEncodingProperties.isForce());
        return filter;

    }


}
