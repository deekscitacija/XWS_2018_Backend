package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.RegisteredUser;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.RegisteredUserRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.service.RegisteredUserService;

@Service
public class RegisteredUserServiceImpl implements RegisteredUserService{

	@Autowired
	private RegisteredUserRepository registeredUserRepository;
	
	@Override
	public RegisteredUser saveRegUser(RegisteredUser user) {
		
		return registeredUserRepository.save(user);
	}

	@Override
	public RegisteredUser getRegUser(Integer id) {
		
		return registeredUserRepository.findOne(id);
	}

}
