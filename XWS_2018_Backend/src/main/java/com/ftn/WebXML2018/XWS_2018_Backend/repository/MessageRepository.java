package com.ftn.WebXML2018.XWS_2018_Backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.Message;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;

public interface MessageRepository extends JpaRepository<Message, Long>{

	public Page<Message> findBySenderOrderByIdDesc(User sender, Pageable pageable);
	
	public Page<Message> findByRecipientOrderByIdDesc(User recipient, Pageable pageable);
	
	public List<Message> findBySenderOrderByIdDesc(User sender);
	
	public List<Message> findByRecipientOrderByIdDesc(User recipient);
}
