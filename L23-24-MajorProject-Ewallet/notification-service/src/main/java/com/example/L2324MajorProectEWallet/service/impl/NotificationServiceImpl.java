package com.example.L2324MajorProectEWallet.service.impl;

import com.example.L2324MajorProectEWallet.service.NotificationService;
import com.example.L2324MajorProjectEWallet.constants.CommonConstants;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    SimpleMailMessage simpleMailMessage;

    @Autowired
    JavaMailSender javaMailSender;
    @Override
    public void sendNotification(String msg) throws ParseException {
        JSONObject data = (JSONObject) new JSONParser().parse(msg);
        String email = (String)data.get(CommonConstants.EMAIL);
        String emailMsg = (String) data.get(CommonConstants.MESSAGE);


        simpleMailMessage.setFrom("ewallet.gfg.33@gmail.com");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText(emailMsg);
        simpleMailMessage.setSubject("E wallet Payment Updates");

        javaMailSender.send(simpleMailMessage);
    }
}
