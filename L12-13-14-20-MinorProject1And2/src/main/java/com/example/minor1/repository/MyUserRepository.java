package com.example.minor1.repository;

import com.example.minor1.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser,Integer> {
    MyUser findByUsername(String username);
}
