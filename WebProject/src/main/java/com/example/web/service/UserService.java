package com.example.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.web.dto.UserDto;
import com.example.web.entity.UserEntity;
import com.example.web.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	
	@Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	 
	

	public void signUpProcess(UserDto user) {
		
		boolean isUserId = repository.existsBymId(user.getM_id());
		if(isUserId) {
			return;
		}
		
		boolean isUserNickName = repository.existsBymNickname(user.getM_nickname());
		if(isUserNickName) {
			return;
		}
		
		UserEntity data = new UserEntity();
		
		data.setMId(user.getM_id());
		data.setMPw(bCryptPasswordEncoder.encode(user.getM_pw())); 
		data.setMEmail(user.getM_email());
		data.setMNickname(user.getM_nickname());
		data.setMNumber(user.getM_number());
		data.setMName(user.getM_name());
		data.setMRole("USER");
		repository.save(data);
	}
}
