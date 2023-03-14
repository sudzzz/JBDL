package com.example.L2324MajorProjectEWallet.repository;

import com.example.L2324MajorProjectEWallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface WalletRepository extends JpaRepository<Wallet,Integer> {

    Wallet findByPhoneNumber(String phoneNumber);
    @Modifying
    @Query("update Wallet w set w.balance = w.balance + ?2 where w.phoneNumber = ?1")
    void updateWallet(String phoneNumber,Double amount);
}
