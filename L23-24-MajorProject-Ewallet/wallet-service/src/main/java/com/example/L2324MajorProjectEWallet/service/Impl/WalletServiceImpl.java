package com.example.L2324MajorProjectEWallet.service.Impl;

import com.example.L2324MajorProjectEWallet.constants.CommonConstants;
import com.example.L2324MajorProjectEWallet.enums.UserIdentifierEnum;
import com.example.L2324MajorProjectEWallet.enums.WalletUpdateStatusEnum;
import com.example.L2324MajorProjectEWallet.model.Wallet;
import com.example.L2324MajorProjectEWallet.repository.WalletRepository;
import com.example.L2324MajorProjectEWallet.service.WalletService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void createWallet(String message) throws ParseException {
        JSONObject data = (JSONObject) new JSONParser().parse(message);

        String phoneNumber = (String) data.get(CommonConstants.USER_CREATION_TOPIC_PHONE_NUMBER);
        Long userId = (Long) data.get(CommonConstants.USER_CREATION_TOPIC_USERID);
        String identifierKey = (String) data.get(CommonConstants.USER_CREATION_TOPIC_IDENTIFIER_KEY);
        String identifierValue = (String) data.get(CommonConstants.USER_CREATION_TOPIC_IDENTIFIER_VALUE);

        Wallet wallet = Wallet.builder()
                .userId(userId)
                .phoneNumber(phoneNumber)
                .userIdentifier(UserIdentifierEnum.valueOf(identifierKey))
                .userIdentifierValue(identifierValue)
                .balance(10.0)
                .build();

        walletRepository.save(wallet);
    }

    @Override
    public void updateWalletsForTransactions(String message) throws ParseException, JsonProcessingException {
        JSONObject data = (JSONObject) new JSONParser().parse(message);

        String sender = (String) data.get(CommonConstants.TRANSACTION_CREATION_TOPIC_SENDER);
        String receiver = (String) data.get(CommonConstants.TRANSACTION_CREATION_TOPIC_RECEIVER);
        Double amount = (Double) data.get(CommonConstants.TRANSACTION_CREATION_TOPIC_AMOUNT);
        String transactionId = (String) data.get(CommonConstants.TRANSACTION_CREATION_TOPIC_TRANSACTION_ID);

        Wallet senderWallet = walletRepository.findByPhoneNumber(sender);
        Wallet receiverWallet = walletRepository.findByPhoneNumber(receiver);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CommonConstants.TRANSACTION_CREATION_TOPIC_TRANSACTION_ID,transactionId);
        jsonObject.put(CommonConstants.TRANSACTION_CREATION_TOPIC_SENDER,sender);
        jsonObject.put(CommonConstants.TRANSACTION_CREATION_TOPIC_RECEIVER,receiver);
        jsonObject.put(CommonConstants.TRANSACTION_CREATION_TOPIC_AMOUNT,amount);

        if(Objects.isNull(senderWallet) || Objects.isNull(receiverWallet) || senderWallet.getBalance()>=amount){
            //fail the transaction
            jsonObject.put(CommonConstants.WALLET_UPDATE_TOPIC_STATUS, WalletUpdateStatusEnum.FAILED);
            kafkaTemplate.send(CommonConstants.WALLET_UPDATE_TOPIC,objectMapper.writeValueAsString(jsonObject));
            return;
        }

        walletRepository.updateWallet(receiver,amount);
        walletRepository.updateWallet(sender,0-amount);

        //TODO : Kafka event for update wallet
        jsonObject.put(CommonConstants.WALLET_UPDATE_TOPIC_STATUS,WalletUpdateStatusEnum.SUCCESS);
        kafkaTemplate.send(CommonConstants.WALLET_UPDATE_TOPIC,objectMapper.writeValueAsString(jsonObject));
    }
}
