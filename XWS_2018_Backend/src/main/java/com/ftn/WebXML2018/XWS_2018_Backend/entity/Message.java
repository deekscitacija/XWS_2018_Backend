package com.ftn.WebXML2018.XWS_2018_Backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = true)
	@Size(max = 1000)
	private String content;
	
	@ManyToOne(optional = false)
	private User sender;
	
	@ManyToOne(optional = false)
	private User recipient; 
	
	public Message() {
		
	}

	public Message(Long id, String content, User sender, User recipient) {
		super();
		this.id = id;
		this.content = content;
		this.sender = sender;
		this.recipient = recipient;
	}
	
	public Message(String content, User sender, User recipient) {
		super();
		this.content = content;
		this.sender = sender;
		this.recipient = recipient;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}
	
}
