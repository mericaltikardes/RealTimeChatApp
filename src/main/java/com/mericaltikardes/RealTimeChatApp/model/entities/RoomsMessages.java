package com.mericaltikardes.RealTimeChatApp.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
public class RoomsMessages {
    @Id
    private String id= UUID.randomUUID().toString();;

    private String sender;
    private String roomName;

    private String message;

    public RoomsMessages() {
    }

    public RoomsMessages(String sender, String roomName, String message) {
        this.sender = sender;
        this.roomName = roomName;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
