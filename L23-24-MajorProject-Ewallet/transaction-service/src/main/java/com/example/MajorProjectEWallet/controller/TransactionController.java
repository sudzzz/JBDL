package com.example.MajorProjectEWallet.controller;

import com.example.MajorProjectEWallet.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    /**
     * Parameters for transaction
     * SenderId
     * ReceiverId
     * Reason
     */

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/txn")
    public String initiateTxn(@RequestParam("receiver") String receiver,
                              @RequestParam("purpose") String purpose,
                              @RequestParam("amount") Double amount) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return transactionService.initiateTxn(user.getUsername(),receiver,purpose,amount);
    }

}
