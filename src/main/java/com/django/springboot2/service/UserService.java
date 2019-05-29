package com.django.springboot2.service;

import com.django.springboot2.pojo.domain.TestUser;

import java.math.BigDecimal;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Administrator
 * @Date 2019/5/9
 * @Version 1.0
 */
public interface UserService {
    public void insert(TestUser user) throws Exception;
    public void del(TestUser user) throws Exception;
    public void update(TestUser user) throws Exception;
    public TestUser queryByPk(BigDecimal userId) throws Exception;
}
