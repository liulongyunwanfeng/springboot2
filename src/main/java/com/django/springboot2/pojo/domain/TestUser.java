package com.django.springboot2.pojo.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName TestUser
 * @Author Administrator
 * @Date 2019/5/9
 * @Version 1.0
 * @Description 用户信息表
 */
public class TestUser implements Serializable {


    private BigDecimal id;
    private String name;
    private Integer sex;
    private Date birthday;
    private String introduce;






    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
