package com.example.L2324MajorProjectEWallet;

import com.example.L2324MajorProjectEWallet.enums.UserIdentifierEnum;
import com.example.L2324MajorProjectEWallet.request.UserCreateRequest;
import com.example.L2324MajorProjectEWallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.L2324MajorProjectEWallet.model.User;

import java.util.List;

@SpringBootApplication
public class UserApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<User> adminList = userService.getAllAdmins();
        if(adminList.isEmpty()){
            UserCreateRequest adminCreateRequest = UserCreateRequest.builder()
                    .name("Sudhir Daga")
                    .email("sudhirdaga18@gmail.com")
                    .phoneNumber("+91-8335940590")
                    .dob("18-08-1998")
                    .password("Sudhir@123")
                    .country("India")
                    .userIdentifier(UserIdentifierEnum.PAN)
                    .userIdentifierValue("PQRST8502Q").build();
            userService.createAdmin(adminCreateRequest);
        }
    }
}
