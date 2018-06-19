package com.ftn.WebXML2018.XWS_2018_Backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class RegisteredUser {

	@Id
	private Long id;
	
	@Column(nullable = true)
	@Size(max = 30)
	private String phoneNumber;
	
	@Column(nullable = false)
	private boolean active;
	
	public RegisteredUser() {
		
	}

	public RegisteredUser(Long id, String email, String phoneNumber, boolean active) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.active = active;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
