package com.example.MajorProjectEWallet.repository;

import com.example.MajorProjectEWallet.enums.TransactionStatusEnum;
import com.example.MajorProjectEWallet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;

@Transactional
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    @Modifying
    @Query("update Transaction t set t.transactionStatus = ?2 where t.transactionId = ?1")
    void updateTxn(String txnId, TransactionStatusEnum transactionStatus);
}
