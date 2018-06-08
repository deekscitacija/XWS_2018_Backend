package com.ftn.WebXML2018.XWS_2018_Backend.service;

import javax.mail.MessagingException;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;

public interface EmailService {

	public void changePassword(User user, String token) throws MessagingException;
	
}
