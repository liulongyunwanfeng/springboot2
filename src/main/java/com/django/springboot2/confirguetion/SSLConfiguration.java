package com.django.springboot2.confirguetion;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SSLConfiguration
 * @Author Administrator
 * @Date 2019/5/17
 * @Version 1.0
 * @Description TODO
 */
@Configuration
public class SSLConfiguration {

    /**
     * @Author django
     * @Date   2019/5/17
     * @param   * @param null
     * @return
     * @Desc   配置http自动转为https
    */
    @Bean
    public ServletWebServerFactory servletWebServerFactory(){
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory(){
        @Override
        protected void postProcessContext(Context context) {
            SecurityConstraint securityConstraint = new SecurityConstraint();
            // 机密的
            securityConstraint.setUserConstraint("CONFIDENTIAL");
            SecurityCollection securityCollection = new SecurityCollection();
            securityCollection.addPattern("/*");
            securityConstraint.addCollection(securityCollection);
            context.addConstraint(securityConstraint);
            }
        };
        factory.addAdditionalTomcatConnectors(httpConnector());
        return factory;
    }


    @Bean
    public Connector httpConnector(){
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(80);
        connector.setSecure(false);
        connector.setRedirectPort(8005);
        return  connector;
    }
}
