package com.django.springboot2.service.serviceImpl;

import com.django.springboot2.pojo.domain.User;
import com.django.springboot2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName UserServiceImpl
 * @Author Administrator
 * @Date 2019/5/9
 * @Version 1.0
 * @Description TODO
 */
@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Override
    public void insert(User user) throws Exception {
        logger.info("=============新增User==============");

    }

    @Override
    public void del(User user) throws Exception {
        logger.info("=============删除User==============");
    }

    @Override
    public void update(User user) throws Exception {
        logger.info("=============更新User==============");

    }

    @Override
    public User queryByPk(BigDecimal userId) throws Exception {
        User user = new User();
        user.setId(userId);
        user.setName("django");
        user.setBirthday(new Date());
        user.setIntroduce("我最帅");
        user.setSex(1);
        return user;
    }
}
