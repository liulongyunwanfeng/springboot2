package com.django.springboot2.service.serviceImpl;

import com.django.springboot2.dao.mapper.UserMapper;
import com.django.springboot2.pojo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liulongyun
 * @create 2019/5/29 20:53
 **/
@Service
public class MyBatisUserServiceImpl {

    @Autowired
    private UserMapper userMapper;

    public User getUser(Long id) throws Exception{
        return  userMapper.getUser(id);
    }

}
