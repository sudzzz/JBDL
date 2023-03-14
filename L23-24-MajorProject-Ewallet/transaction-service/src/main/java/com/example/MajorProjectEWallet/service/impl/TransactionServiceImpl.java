package com.example.MajorProjectEWallet.service.impl;

import com.example.L2324MajorProjectEWallet.constants.CommonConstants;
import com.example.L2324MajorProjectEWallet.enums.WalletUpdateStatusEnum;
import com.example.MajorProjectEWallet.enums.TransactionStatusEnum;
import com.example.MajorProjectEWallet.model.Transaction;
import com.example.MajorProjectEWallet.repository.TransactionRepository;
import com.example.MajorProjectEWallet.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JSONObject requestedUser = getUserFromUserService(username);
        List<GrantedAuthority> authorities;
        List<LinkedHashMap<String,String>> requestedAuthorities = (List<LinkedHashMap<String, String>>) requestedUser.get("authorities");
        authorities = requestedAuthorities.stream()
                .map(x -> x.get("authority"))
                .map(x -> new SimpleGrantedAuthority(x))
                .collect(Collectors.toList());

        return new User((String) requestedUser.get("username"),
                (String) requestedUser.get("password"),
                authorities);
    }

    @Override
    public String initiateTxn(String sender, String receiver, String purpose, Double amount) throws JsonProcessingException {
        logger.info("Inside initiateTxn method with sender {}",sender);
        Transaction transaction = Transaction.builder()
                .sender(sender)
                .receiver(receiver)
                .purpose(purpose)
                .transactionId(UUID.randomUUID().toString())
                .transactionStatus(TransactionStatusEnum.PENDING)
                .amount(amount)
                .build();

        transactionRepository.save(transaction);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CommonConstants.TRANSACTION_CREATION_TOPIC_SENDER, sender);
        jsonObject.put(CommonConstants.TRANSACTION_CREATION_TOPIC_RECEIVER, receiver);
        jsonObject.put(CommonConstants.TRANSACTION_CREATION_TOPIC_AMOUNT, amount);
        jsonObject.put(CommonConstants.TRANSACTION_CREATION_TOPIC_TRANSACTION_ID, transaction.getTransactionId());

        kafkaTemplate.send(CommonConstants.TRANSACTION_CREATION_TOPIC, objectMapper.writeValueAsString(jsonObject));

        return transaction.getTransactionId();
    }

    @Override
    public void updateTxn(String message) throws ParseException, JsonProcessingException {
        JSONObject data = (JSONObject) new JSONParser().parse(message);

        String transactionId = (String) data.get(CommonConstants.TRANSACTION_CREATION_TOPIC_TRANSACTION_ID);
        String sender = (String) data.get(CommonConstants.TRANSACTION_CREATION_TOPIC_SENDER);
        String receiver = (String) data.get(CommonConstants.TRANSACTION_CREATION_TOPIC_RECEIVER);
        Double amount = (Double) data.get(CommonConstants.TRANSACTION_CREATION_TOPIC_AMOUNT);
        WalletUpdateStatusEnum walletUpdateStatus = WalletUpdateStatusEnum.valueOf((String) data.get(CommonConstants.WALLET_UPDATE_TOPIC_STATUS));

        JSONObject senderObj = getUserFromUserService(sender);

        String receiverEmail = null;

        String senderEmail = (String) senderObj.get(CommonConstants.EMAIL);

        if(walletUpdateStatus == WalletUpdateStatusEnum.SUCCESS){
            JSONObject receiverObj = getUserFromUserService(receiver);
            receiverEmail = (String) receiverObj.get(CommonConstants.EMAIL);
            transactionRepository.updateTxn(transactionId,TransactionStatusEnum.SUCCESSFUL);

            JSONObject receiverEmailObj = new JSONObject();
            String receiverMessage = "Hi, your wallet with phone number "+receiver+" got credited with amount "+amount+" from "+sender;

            receiverEmailObj.put(CommonConstants.EMAIL,receiverEmail);
            receiverEmailObj.put(CommonConstants.MESSAGE,receiverMessage);
            kafkaTemplate.send(CommonConstants.TRANSACTION_COMPLETED_TOPIC,objectMapper.writeValueAsString(receiverEmailObj));
        } else {
            transactionRepository.updateTxn(transactionId,TransactionStatusEnum.FAILED);
            String senderMessage = "Hi, your transaction with id "+transactionId+" got "+walletUpdateStatus;

            JSONObject senderEmailObj = new JSONObject();
            senderEmailObj.put(CommonConstants.EMAIL,senderEmail);
            senderEmailObj.put(CommonConstants.MESSAGE,senderMessage);
            kafkaTemplate.send(CommonConstants.TRANSACTION_COMPLETED_TOPIC,objectMapper.writeValueAsString(senderEmailObj));
        }
    }


    private JSONObject getUserFromUserService(String username){
        String url = "http://localhost:9000/admin/user/"+username;
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setBasicAuth("transaction-service","txn@123");

        HttpEntity request = new HttpEntity(httpHeaders);
        return restTemplate.exchange(url, HttpMethod.GET,request,JSONObject.class).getBody();
    }
}
