package com.django.springboot2.dao.mapper;

import com.django.springboot2.pojo.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @author liulongyun
 * @create 2019/5/29 20:14
 **/

@Repository
public interface UserMapper {
    public User getUser(Long id);
}
