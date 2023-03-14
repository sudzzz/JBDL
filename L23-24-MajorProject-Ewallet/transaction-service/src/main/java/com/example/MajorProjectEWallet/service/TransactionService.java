package com.example.MajorProjectEWallet.service;

import com.example.L2324MajorProjectEWallet.constants.CommonConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface TransactionService extends UserDetailsService {

    String initiateTxn(String sender, String receiver, String purpose, Double amount) throws JsonProcessingException;
    @KafkaListener(topics = CommonConstants.WALLET_UPDATE_TOPIC,groupId = "group123")
    void updateTxn(String message) throws ParseException, JsonProcessingException;
}
