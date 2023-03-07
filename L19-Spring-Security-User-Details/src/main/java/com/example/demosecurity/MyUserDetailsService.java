package com.example.demosecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Logic for getting the user details

        return myUserRepository.findByEmail(username);
    }

    public void createUser(MyUser myUser) {
        try {
            myUserRepository.save(myUser);
        }catch (DuplicateKeyException e){

        }
    }
}
