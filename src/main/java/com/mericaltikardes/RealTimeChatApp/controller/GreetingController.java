package com.mericaltikardes.RealTimeChatApp.controller;

import com.mericaltikardes.RealTimeChatApp.model.HelloMessage;
import com.mericaltikardes.RealTimeChatApp.model.JoinMessage;
import com.mericaltikardes.RealTimeChatApp.model.entities.RoomsMessages;
import com.mericaltikardes.RealTimeChatApp.model.entities.UserSessionDatas;
import com.mericaltikardes.RealTimeChatApp.service.MessageServices;
import com.mericaltikardes.RealTimeChatApp.service.UserService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {

    UserService userService;

    MessageServices messageServices;
    String name = "";
    String roomName="";
    public GreetingController(UserService userService,MessageServices messageServices) {
        this.userService = userService;
        this.messageServices=messageServices;
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
         roomName = roomMessage.getRoomName();
        if(!name.equals("")){
            userService.saveUser(new UserSessionDatas(name,roomName));
        }
    }

    @MessageMapping("/join/{roomName}")
    @SendTo("/topic/greetings/{roomName}")
    public void subscribeRoom(@Payload Map<String,String> message, @DestinationVariable String roomName, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        System.out.println("Received message: " + message.get("message") + " from room: " + roomName+" by:"+message.get("name"));
        RoomsMessages messages=new RoomsMessages(message.get("name"),roomName,message.get("message"));
        messageServices.saveMessages(messages);
        List<RoomsMessages> getRoomMessages=messageServices.getMessagesByRoom(roomName);

    }


}