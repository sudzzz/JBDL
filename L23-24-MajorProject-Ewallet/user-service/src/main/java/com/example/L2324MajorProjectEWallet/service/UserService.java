package com.example.L2324MajorProjectEWallet.service;


import com.example.L2324MajorProjectEWallet.model.User;
import com.example.L2324MajorProjectEWallet.request.UserCreateRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User createUser(UserCreateRequest userCreateRequest) throws JsonProcessingException;

    User createAdmin(UserCreateRequest userCreateRequest);
    List<User> getAllUsers();

    List<User> getAllAdmins();

}
