package com.example.minor1.service.Impl;

import com.example.minor1.model.MyUser;
import com.example.minor1.repository.MyUserCacheRepository;
import com.example.minor1.repository.MyUserRepository;
import com.example.minor1.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MyUserCacheRepository myUserCacheRepository;

    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${users.student.authority}")
    private String studentAuthority;

    @Value("${users.admin.authority}")
    private String adminAuthority;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = myUserCacheRepository.get(username);
        if(Objects.isNull(myUser)){
            myUser = myUserRepository.findByUsername(username);
            if(Objects.isNull(myUser)){
                throw new UsernameNotFoundException("Username not present in db");
            }
            else{
                myUserCacheRepository.set(myUser); //This call can be made parallel.
            }
        }
        return myUser;
    }

    public MyUser createUser(UserCreateRequest userCreateRequest){

        MyUser.MyUserBuilder myUserBuilder = MyUser.builder()
                .username(userCreateRequest.getUsername())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()));

        if(Objects.nonNull(userCreateRequest.getStudent())){
            myUserBuilder.authority(studentAuthority);
        }else{
            myUserBuilder.authority(adminAuthority);
        }
        return myUserRepository.save(myUserBuilder.build());
    }
}
