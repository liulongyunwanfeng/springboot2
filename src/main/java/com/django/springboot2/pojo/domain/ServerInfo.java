package com.django.springboot2.pojo.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName ServerInfo
 * @Author Administrator
 * @Date 2019/5/9
 * @Version 1.0
 * @Description TODO
 */
@Component
@ConfigurationProperties(prefix = "server")
public class ServerInfo {

    private String port;
    private String desplayName;
    private String desc;




    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDesplayName() {
        return desplayName;
    }

    public void setDesplayName(String desplayName) {
        this.desplayName = desplayName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ServerInfo{" +
                "port='" + port + '\'' +
                ", desplayName='" + desplayName + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
