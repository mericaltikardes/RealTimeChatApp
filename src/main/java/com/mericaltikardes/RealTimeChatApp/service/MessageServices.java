package com.mericaltikardes.RealTimeChatApp.service;

import com.mericaltikardes.RealTimeChatApp.model.entities.RoomsMessages;
import com.mericaltikardes.RealTimeChatApp.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageServices {

    MessageRepository messageRepository;

    public MessageServices(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveMessages(RoomsMessages roomsMessages) {
        messageRepository.save(roomsMessages);
    }
}
