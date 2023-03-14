package com.example.L2324MajorProjectEWallet.service.Impl;

import com.example.L2324MajorProjectEWallet.constants.CommonConstants;
import com.example.L2324MajorProjectEWallet.repository.UserRepository;
import com.example.L2324MajorProjectEWallet.request.UserCreateRequest;
import com.example.L2324MajorProjectEWallet.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.L2324MajorProjectEWallet.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${users.user.authority}")
    private String userAuthority;

    @Value("${users.service.authority}")
    private String serviceAuthority;

    @Value("${users.admin.authority}")
    private String adminAuthority;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(username);
    }

    @Override
    public User createUser(UserCreateRequest userCreateRequest) throws JsonProcessingException {
        User user = userCreateRequest.to();
        user.setPassword(encryptPwd(user.getPassword()));
        user.setAuthorities(userAuthority);
        user =  userRepository.save(user);


        // TODO :  Publish an event post user creation which can be listened by other consumers

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CommonConstants.USER_CREATION_TOPIC_USERID,user.getId());
        jsonObject.put(CommonConstants.USER_CREATION_TOPIC_PHONE_NUMBER,user.getPhoneNumber());
        jsonObject.put(CommonConstants.USER_CREATION_TOPIC_IDENTIFIER_VALUE,user.getUserIdentifierValue());
        jsonObject.put(CommonConstants.USER_CREATION_TOPIC_IDENTIFIER_KEY,user.getUserIdentifier());


        kafkaTemplate.send(CommonConstants.USER_CREATION_TOPIC,objectMapper.writeValueAsString(jsonObject));

        return user;
    }

    @Override
    public User createAdmin(UserCreateRequest userCreateRequest){
        User admin = userCreateRequest.to();
        admin.setPassword(encryptPwd(admin.getPassword()));
        admin.setAuthorities(adminAuthority);
        return userRepository.save(admin);
    }

    @Override
    public User createService(UserCreateRequest userCreateRequest) {
        User service = userCreateRequest.to();
        service.setPassword(encryptPwd(service.getPassword()));
        service.setAuthorities(serviceAuthority);
        return userRepository.save(service);
    }

    @Override
    public List<User> getAllUsers() {
       return userRepository.findByAuthorities(userAuthority);
    }

    @Override
    public List<User> getAllAdmins() {
        return userRepository.findByAuthorities(adminAuthority);
    }

    @Override
    public List<User> getAllServices() {
        return userRepository.findByAuthorities(serviceAuthority);
    }

    private String encryptPwd(String rawPwd){
        return passwordEncoder.encode(rawPwd);
    }
}
