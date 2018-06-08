package com.ftn.WebXML2018.XWS_2018_Backend.service;

import java.util.List;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.PasswordResetToken;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;

public interface PasswordResetTokenService {
	
	public PasswordResetToken generateToken(PasswordResetToken token);
	
	public List<PasswordResetToken> getTokensByUser(User user);

}
