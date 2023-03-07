package com.example.L2324MajorProjectEWallet.service;

import com.example.L2324MajorProjectEWallet.constants.CommonConstants;
import org.springframework.kafka.annotation.KafkaListener;

public interface WalletService {
    @KafkaListener(topics = CommonConstants.USER_CREATION_TOPIC,groupId = "group123")
    void createWallet(String message);

}
