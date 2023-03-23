package com.mericaltikardes.RealTimeChatApp.controller;

import com.mericaltikardes.RealTimeChatApp.model.Greeting;
import com.mericaltikardes.RealTimeChatApp.model.HelloMessage;
import com.mericaltikardes.RealTimeChatApp.model.JoinMessage;
import com.mericaltikardes.RealTimeChatApp.model.entities.UserSessionDatas;
import com.mericaltikardes.RealTimeChatApp.service.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

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

    @MessageMapping("/join")
    @SendTo("/topic/greetings")
    public void joinRoom(JoinMessage roomMessage) {
        String roomName = roomMessage.getRoomName();
        if(!name.equals("")){
            userService.saveUser(new UserSessionDatas(name,roomName));
        }
    }

}