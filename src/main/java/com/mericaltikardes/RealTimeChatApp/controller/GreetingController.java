package com.mericaltikardes.RealTimeChatApp.controller;

import com.mericaltikardes.RealTimeChatApp.model.Greeting;
import com.mericaltikardes.RealTimeChatApp.model.HelloMessage;
import com.mericaltikardes.RealTimeChatApp.model.JoinMessage;
import com.mericaltikardes.RealTimeChatApp.model.entities.UserSessionDatas;
import com.mericaltikardes.RealTimeChatApp.service.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    UserService userService;
    String name="";
    public GreetingController(UserService userService) {
        this.userService = userService;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        name=message.getName();
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/join")
    @SendTo("/topic/greetings")
    public void joinRoom(JoinMessage roomMessage) {
        UserSessionDatas userSessionDatas = new UserSessionDatas(name, roomMessage.getRoomName());
        userService.saveUser(userSessionDatas);
    }
}