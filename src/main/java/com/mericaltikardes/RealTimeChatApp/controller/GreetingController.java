package com.mericaltikardes.RealTimeChatApp.controller;

import com.mericaltikardes.RealTimeChatApp.model.HelloMessage;
import com.mericaltikardes.RealTimeChatApp.model.JoinMessage;
import com.mericaltikardes.RealTimeChatApp.model.entities.UserSessionDatas;
import com.mericaltikardes.RealTimeChatApp.service.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {

    UserService userService;

    String name = "";

    public GreetingController(UserService userService) {
        this.userService = userService;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public void greeting(HelloMessage message) throws Exception {
        name=message.getName();
    }
//Burddan mapping mi yapmak stomptan mı yollamak daha amnatıklı pek emin değilim
    @MessageMapping("/join")
    @SendTo("/topic/greetings")
    public void joinRoom(JoinMessage roomMessage) {
        String roomName = roomMessage.getRoomName();
        if(!name.equals("")){
            userService.saveUser(new UserSessionDatas(name,roomName));
        }
    }

}