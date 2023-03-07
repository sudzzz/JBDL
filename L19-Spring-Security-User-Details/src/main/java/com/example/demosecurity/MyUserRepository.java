package com.example.demosecurity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface MyUserRepository extends JpaRepository<MyUser, Integer> {

    MyUser findByEmail(String u);
}
