package com.example.L2324MajorProjectEWallet.config;

import com.example.L2324MajorProjectEWallet.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Value("${users.user.authority}")
    private String userAuthority;

    @Value("${users.service.authority}")
    private String serviceAuthority;

    @Value("${users.admin.authority}")
    private String adminAuthority;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()//added so that the response of api will e in json form and not text/html.
                .and()
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.POST,"/user/**").permitAll()       //sign-up of new account
                .antMatchers("/user/**").hasAuthority(userAuthority)       //user driven actions
                .antMatchers("/**").hasAnyAuthority(adminAuthority,serviceAuthority)           //admin driven actions
                .and()
                .formLogin();
    }

}
