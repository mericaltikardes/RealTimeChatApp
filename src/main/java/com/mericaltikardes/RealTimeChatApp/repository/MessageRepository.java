package com.mericaltikardes.RealTimeChatApp.repository;

import com.mericaltikardes.RealTimeChatApp.model.entities.RoomsMessages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<RoomsMessages,String> {

   public List<RoomsMessages> findByRoomName(String roomName);
}
