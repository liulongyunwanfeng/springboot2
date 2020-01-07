package com.django.springboot2.confirguetion;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * @author liulongyun
 * @create 2019/6/9 11:18
 **/
//@Configuration
public class InMemrorySecurityConfigure extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> userconfig = auth.inMemoryAuthentication();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        userconfig.passwordEncoder(passwordEncoder);
        userconfig.withUser("lly")
                    .password(passwordEncoder.encode("abcdef"))
                    .roles("USER","ADMIN");

        userconfig.withUser("django")
                    .password(passwordEncoder.encode("123456"))
                    .roles("USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * 一：关闭csrf
         * 原因CSRF默认支持的方法： GET|HEAD|TRACE|OPTIONS，不支持POST。
         * 传统的session id容易被第三方窃取攻击，spring security4.0版本之后，引入了CSRF的概念。
         * spring security为了正确的区别合法的post请求，采用了token的机制。
         * 过程大致为get请求会从服务器端拿到一个token,这个token被拿来当做header参数通过post请求传递至服务器。
         * 服务器通过区分这个token值是否合法来判定是否是正常的post请求（而非第三方攻击）
         *
         *  二：显然，关闭csrf是不明智的，所有，我们可以让前端发post请求的时候带上X-CSRF-TOKEN请求头
         *  如：
         *  X-CSRF-TOKEN: 3ff3a140-e056-4a8e-afb7-e66237a03545
         */
//        http.csrf().disable();
    }
}
