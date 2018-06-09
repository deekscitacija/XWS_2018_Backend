package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.PasswordResetToken;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.PasswordResetTokenRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.service.PasswordResetTokenService;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService{

	@Autowired
	public PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Override
	public PasswordResetToken generateToken(PasswordResetToken token) {

		return passwordResetTokenRepository.save(token);
	}

	@Override
	public List<PasswordResetToken> getTokensByUser(User user) {
		
		return passwordResetTokenRepository.getByUserOrderByIdDesc(user);
	}

}
