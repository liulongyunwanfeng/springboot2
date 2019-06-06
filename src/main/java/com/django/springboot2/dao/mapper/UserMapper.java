package com.django.springboot2.dao.mapper;

import com.django.springboot2.pojo.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liulongyun
 * @create 2019/5/29 20:14
 **/

@Repository
public interface UserMapper {
     User getUser(Long id);
     int insert(User user);
     void updateUser(User user);
     List<User> findUsers(@Param("userName") String userName, @Param("note") String note);
     int deleteUser(Long id);
}
