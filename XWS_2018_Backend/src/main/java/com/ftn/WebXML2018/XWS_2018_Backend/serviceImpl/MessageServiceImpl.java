package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.Message;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.MessageRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageRepository messageRepository;
	
	@Override
	public Message saveMessage(Message message) {
		
		return messageRepository.save(message);
	}

	@Override
	public Page<Message> findBySenderOrRecipient(User user, boolean sender, Pageable pageable) {
		
		if(sender) {
			return messageRepository.findBySenderOrderByIdDesc(user, pageable);
		}
		
		return messageRepository.findByRecipientOrderByIdDesc(user, pageable);
	}

	@Override
	public List<Message> findBySenderOrRecipientNonPageable(User user, boolean sender) {
		if(sender) {
			return messageRepository.findBySenderOrderByIdDesc(user);
		}
		
		return messageRepository.findByRecipientOrderByIdDesc(user);
	}

}
