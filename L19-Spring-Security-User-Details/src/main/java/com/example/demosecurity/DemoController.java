package com.example.demosecurity;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String greet(){
        return "Hello World!!";
    }

    @GetMapping("/demo2")
    public String greet2(){
        return "Hello World2!!";
    }

    @GetMapping("/testcode")
    public String testCode(){
        return "Testing the code....";
    }

    @GetMapping("/developcode")
    public String developCode(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal(); // returns the user which is stored in application context.

        return myUser.getName() + " is developing the code....";
    }

    @GetMapping("/accessserver")
    public String accessServer(){
        return "Accessing the server....";
    }

    @PostMapping("/home")
    public String home(){
        return "Welcome to the home page....";
    }

    @GetMapping("/home/all")
    public String homeAll(){
        return "Welcome to the home page all....";
    }

    // sign up API to create user
    @PostMapping("/signup")
    public void signup(@RequestParam("name")String name,
                       @RequestParam("email") String email,
                       @RequestParam("password") String password, // encrypted pwd : algorithm
                       @RequestParam("authority") String authority){

    }

    // Sign up
    // End user - Rahul123
    // FE --> BE (2$%SFFWY2J: P1, Alg: ABC)
    // BE --> P2 ~ P1 in our Algo : BCrypt
    // BE --> P2 will be stored in DB


    // End user -- Rahul 123
    // FE --> BE (P3, Alg: ABC)
    //

    // Unsafe methods can not be permit all with csrf enabled

    // 100% of you will fail in this

    // 03885c7d-7e88-4d6e-895c-63cc94bd5d32

    // 5608F241C6961F59FA4184D712ADF209 - session id of previously logged in user
}

/*
        Request comes to the BE
        1. JSESSIONID sent is of authenticated session / user
            BE work : verify the session id, return the response of the request resource
        2. JSESSIONID sent is of unauthenticated session / user
            BE work: verify the session id, redirect to /login API
            End user: pass the credentials
            FE: makes a call to /login API with unauthenticated JSESSIONID + payload (username and password)
            BE: verify on basis of username and pwd  and generate a new authenticated session and return the id of that
                as a set-cookie in the response header

 */