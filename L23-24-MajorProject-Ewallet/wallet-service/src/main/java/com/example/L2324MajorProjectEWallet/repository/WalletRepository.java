package com.example.L2324MajorProjectEWallet.repository;

import com.example.L2324MajorProjectEWallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet,Integer> {
}
