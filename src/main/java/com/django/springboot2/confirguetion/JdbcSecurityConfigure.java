package com.django.springboot2.confirguetion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author liulongyun
 * @create 2019/6/9 20:30
 **/
@Configuration
public class JdbcSecurityConfigure extends WebSecurityConfigurerAdapter {
   /* @Value("${system.user.password.secret}")
    private String secret = null;*/
//    spring 默认的JdbcSecurityConfigure
//    @Autowired
//    private DataSource dataSource;
//
//    // 使用用户名称查询密码
//    String pwdQuery = " select user_name, pwd, available" + " from t_user where user_name = ?";
//    // 使用用户名称查询角色信息
//    String roleQuery = " select u.user_name, r.role_name " + " from t_user u, t_user_role ur, t_role r "
//            + "where u.id = ur.user_id and r.id = ur.role_id" + " and u.user_name = ?";
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        auth.jdbcAuthentication()
//            .passwordEncoder(passwordEncoder)
//            .dataSource(dataSource)
//            .usersByUsernameQuery(pwdQuery)
//            .authoritiesByUsernameQuery(roleQuery);
//
//
//
//
//    }

    //使用自定义的UserDetailsService



    @Resource(name = "userDetailsServiceImpl")
    private UserDetailsService userDetailsService = null;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {



        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();
    }


}
