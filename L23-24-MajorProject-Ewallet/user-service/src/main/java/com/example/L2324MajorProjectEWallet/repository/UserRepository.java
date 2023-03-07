package com.example.L2324MajorProjectEWallet.repository;

import com.example.L2324MajorProjectEWallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByPhoneNumber(String phoneNumber);

    List<User> findByAuthorities(String authority);
}
