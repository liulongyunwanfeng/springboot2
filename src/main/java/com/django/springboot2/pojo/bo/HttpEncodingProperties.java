package com.django.springboot2.pojo.bo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * @ClassName HttpEncodingProperties
 * @Author Administrator
 * @Date 2019/5/10
 * @Version 1.0
 * @Description
 * http请求编码设置对象
 */

@ConfigurationProperties(prefix = "spring.http.encoding")
public class HttpEncodingProperties {

    private static final Charset DEFAULT_CHARTSET= Charset.forName("UTF-8");

    private Charset charset = DEFAULT_CHARTSET;

    private boolean force = true;

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }
}
