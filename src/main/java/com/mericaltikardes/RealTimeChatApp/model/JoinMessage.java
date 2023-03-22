package com.mericaltikardes.RealTimeChatApp.model;

public class JoinMessage {

    private String roomName;

    public JoinMessage() {
    }

    public JoinMessage(String room) {
        this.roomName = room;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String room) {
        this.roomName = room;
    }
}
