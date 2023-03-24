package com.mericaltikardes.RealTimeChatApp.repository;

import com.mericaltikardes.RealTimeChatApp.model.entities.RoomsMessages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<RoomsMessages,String> {

}
