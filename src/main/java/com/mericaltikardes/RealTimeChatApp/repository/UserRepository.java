package com.mericaltikardes.RealTimeChatApp.repository;


import com.mericaltikardes.RealTimeChatApp.model.entities.UserSessionDatas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserSessionDatas,String> {
}
