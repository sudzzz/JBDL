package com.example.L2324MajorProjectEWallet.service.Impl;

import com.example.L2324MajorProjectEWallet.constants.CommonConstants;
import com.example.L2324MajorProjectEWallet.enums.UserIdentifierEnum;
import com.example.L2324MajorProjectEWallet.model.Wallet;
import com.example.L2324MajorProjectEWallet.repository.WalletRepository;
import com.example.L2324MajorProjectEWallet.service.WalletService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public void createWallet(String message) {
        JSONObject data = objectMapper.convertValue(message, JSONObject.class);

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
}
