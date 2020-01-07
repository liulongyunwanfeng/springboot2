package com.django.springboot2.service.serviceImpl;

import com.django.springboot2.pojo.domain.Role;
import com.django.springboot2.pojo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liulongyun
 * @create 2019/6/9 22:01
 **/
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MyBatisUserServiceImpl myBatisUserService = null;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = myBatisUserService.selectUserByUserName(username);
        List<Role> roles = myBatisUserService.selectRoleByUserName(username);



        return  changeToUserDetail(user,roles);
    }

    private UserDetails changeToUserDetail(User user, List<Role> roles) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role:roles) {
            GrantedAuthority au = new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(au);
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPwd(),authorities);

        return userDetails;
    }
}
