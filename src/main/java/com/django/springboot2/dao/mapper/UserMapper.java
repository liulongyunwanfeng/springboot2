package com.django.springboot2.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.django.springboot2.pojo.domain.Role;
import com.django.springboot2.pojo.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liulongyun
 * @create 2019/5/29 20:14
 **/

@Repository
public interface UserMapper  extends BaseMapper<User> {
     List<User> findUsers(@Param("userName") String userName, @Param("note") String note);

     User selectByUserNameAndPwd(@Param("userName")String userName, @Param("pwd")String pwd);
     User selectUserByUserName(@Param("userName")String userName);


     List<Role> selectRoleByUserName(@Param("userName") String userName);


}
