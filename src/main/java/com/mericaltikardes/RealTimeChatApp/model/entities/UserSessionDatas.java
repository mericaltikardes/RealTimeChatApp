package com.mericaltikardes.RealTimeChatApp.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@Entity
public class UserSessionDatas {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String roomName;

    public UserSessionDatas(String name, String roomName) {
        this.name = name;
        this.roomName = roomName;
    }

    public UserSessionDatas() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
