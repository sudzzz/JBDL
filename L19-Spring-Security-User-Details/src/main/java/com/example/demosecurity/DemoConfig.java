package com.example.demosecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DemoConfig extends WebSecurityConfigurerAdapter {


    // user , default pwd

    /**
     * UserDetailsService: Service used to get the user's information / details like username, pwd, authorities etc.
     * UserDetails: Interface which should be implemented by your entity class which is going to be stored in db
     * UserNamePasswordAuthenticationFilter:
     * in memory authentication --> usernamepasswordauthenticationfilter
            getting the user details from some class which is getting the info from memory
     * user details service authentication --> usernamepasswordauthenticationfilter
            getting the user details from our custom class which we have written which is getting the info from external datasource
     **/

    @Autowired
    UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);

    }

    // facebook, linkedin: sign up : GET / POST

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf()
//                .disable() // Now permitAll will work with unsafe methods also
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/testcode/**").hasAuthority("qa")
                .antMatchers("/developcode/**").hasAuthority("dev")
                .antMatchers("/accessserver/**").hasAnyAuthority("dev", "qa")
                .antMatchers("/home}").permitAll()
                .and()
                .formLogin();

        /* role based access --> your ant matchers can have multiple roles for a set of APIs
                                 and your user in db will have a single role in the authority's column

         */
        /* action based access --> your ant matcher will have only 1 action for a set of APIs and your user in db will
                                   have multiple actions in the authority's column
         */
        /*
           role and action bases access --> combination of above two
         */
    }

    @Bean
    PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }

    // 2 Apples: 1 is stale and 1 is fresh
    // 1 whole Apple + 1 apple juice :

    /*
         1 rp, 1ep
         2 rp ==
         2 ep == convert 1 rp --> ep
     */

    /*
                                      CTO
                                    /    \
                                   vp1    vp2
                                   /  \    /  \
                                  em1  em2 em3 em4
     */
}
