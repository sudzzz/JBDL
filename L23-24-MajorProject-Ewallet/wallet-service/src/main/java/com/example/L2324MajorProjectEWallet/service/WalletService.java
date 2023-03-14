package com.example.L2324MajorProjectEWallet.service;

import com.example.L2324MajorProjectEWallet.constants.CommonConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.springframework.kafka.annotation.KafkaListener;

public interface WalletService {
    @KafkaListener(topics = CommonConstants.USER_CREATION_TOPIC,groupId = "group123")
    void createWallet(String message) throws ParseException;

    @KafkaListener(topics = CommonConstants.TRANSACTION_CREATION_TOPIC,groupId = "group123")
    void updateWalletsForTransactions(String message) throws ParseException, JsonProcessingException;
}
