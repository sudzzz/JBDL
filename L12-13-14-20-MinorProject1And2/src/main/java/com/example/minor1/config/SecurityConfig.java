package com.example.minor1.config;

import com.example.minor1.service.Impl.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // authorisation + authentication + PasswordEncoder + Redis related beans
    @Autowired
    MyUserDetailsServiceImpl myUserDetailsService;

    @Value("${users.admin.authority}")
    String adminAuthority;

    @Value("${users.student.authority}")
    String studentAuthority;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/student_for_admin/**").hasAnyAuthority(adminAuthority)
                .antMatchers(HttpMethod.POST,"/student/**","/book/**","/transaction/**").permitAll()
                .antMatchers("/student/**","/transaction/**").hasAnyAuthority(studentAuthority)
                .antMatchers("/books/search/**").hasAnyAuthority(studentAuthority,adminAuthority)
                .antMatchers("/book/**").hasAnyAuthority(adminAuthority)
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();

    }

}
