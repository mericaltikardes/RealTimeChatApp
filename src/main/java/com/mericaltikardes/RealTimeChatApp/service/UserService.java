package com.mericaltikardes.RealTimeChatApp.service;

import com.mericaltikardes.RealTimeChatApp.model.entities.UserSessionDatas;
import com.mericaltikardes.RealTimeChatApp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserSessionDatas user){
        userRepository.save(user);
    }
}
