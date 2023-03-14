package com.example.L2324MajorProectEWallet.service;

import com.example.L2324MajorProjectEWallet.constants.CommonConstants;
import org.json.simple.parser.ParseException;
import org.springframework.kafka.annotation.KafkaListener;

public interface NotificationService {

    @KafkaListener(topics = {CommonConstants.TRANSACTION_COMPLETED_TOPIC}, groupId = "group123")
    void sendNotification(String msg) throws ParseException;
}
