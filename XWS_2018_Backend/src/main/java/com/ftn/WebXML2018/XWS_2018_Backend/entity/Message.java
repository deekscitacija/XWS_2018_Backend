package com.ftn.WebXML2018.XWS_2018_Backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = true)
	@Size(max = 1000)
	private String content;
	
	@OneToOne(optional = false)
	private RegisteredUser registeredUser;
	
	@OneToOne(optional = false)
	private AgentUser agentUser; 
	
	public Message() {
		
	}

	public Message(Long id, String content, RegisteredUser registeredUser, AgentUser agentUser) {
		super();
		this.id = id;
		this.content = content;
		this.registeredUser = registeredUser;
		this.agentUser = agentUser;
	}
	
	public Message(String content, RegisteredUser registeredUser, AgentUser agentUser) {
		super();
		this.content = content;
		this.registeredUser = registeredUser;
		this.agentUser = agentUser;
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

	public RegisteredUser getRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
	}

	public AgentUser getAgentUser() {
		return agentUser;
	}

	public void setAgentUser(AgentUser agentUser) {
		this.agentUser = agentUser;
	}
	
}
