package com.example.minor1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CommonConfig {
    @Bean
    public PasswordEncoder getpasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * We can make a common generic function to implement redis cache on all the get apis of the project
     *          T getData(Repository class ref, Cache class ref){
     *
     *          }
     */
}
