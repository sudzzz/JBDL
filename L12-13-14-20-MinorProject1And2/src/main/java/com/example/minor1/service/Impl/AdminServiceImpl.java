package com.example.minor1.service.Impl;

import com.example.minor1.model.Admin;
import com.example.minor1.model.MyUser;
import com.example.minor1.repository.AdminRepository;
import com.example.minor1.request.StudentCreateRequest;
import com.example.minor1.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsService;

    public Admin create(StudentCreateRequest studentCreateRequest){
        UserCreateRequest userCreateRequest = studentCreateRequest.toAdminUser();
        MyUser myUser = myUserDetailsService.createUser(userCreateRequest);
        Admin admin = studentCreateRequest.toAdmin();
        admin.setMyUser(myUser);
        return adminRepository.save(admin);
    }

    public List<Admin> getAllAdmin(){
        return adminRepository.findAll();
    }
}
