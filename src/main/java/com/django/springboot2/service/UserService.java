package com.django.springboot2.service;

import com.django.springboot2.pojo.domain.User;

import java.math.BigDecimal;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Administrator
 * @Date 2019/5/9
 * @Version 1.0
 */
public interface UserService {
    public void insert(User user) throws Exception;
    public void del(User user) throws Exception;
    public void update(User user) throws Exception;
    public User queryByPk(BigDecimal userId) throws Exception;
}
