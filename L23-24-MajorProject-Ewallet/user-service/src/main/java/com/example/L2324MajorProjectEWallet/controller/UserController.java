package com.example.L2324MajorProjectEWallet.controller;

import com.example.L2324MajorProjectEWallet.model.User;
import com.example.L2324MajorProjectEWallet.request.UserCreateRequest;
import com.example.L2324MajorProjectEWallet.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody UserCreateRequest userCreateRequest) throws JsonProcessingException {
        return new ResponseEntity<>(userService.createUser(userCreateRequest), HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return new ResponseEntity<>((User) userService.loadUserByUsername(user.getUsername()),HttpStatus.ACCEPTED);

    }

    @GetMapping("/admin/all/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.ACCEPTED);
    }
}
