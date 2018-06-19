package com.ftn.WebXML2018.XWS_2018_Backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.Message;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;

public interface MessageService {
	
	public Message saveMessage(Message message);
	
	public Page<Message> findBySenderOrRecipient(User user, boolean sender, Pageable pageable);

}
