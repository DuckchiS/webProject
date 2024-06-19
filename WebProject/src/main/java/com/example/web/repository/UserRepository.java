package com.example.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.web.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	boolean existsBymId(String mId);
	boolean existsBymNickname(String mNickname);
	
	UserEntity findBymId(String mId); // 사용자 정보를 가져오는 메서드 추가
}
