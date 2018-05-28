package com.ftn.WebXML2018.XWS_2018_Backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class AgentUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Size(max = 90)
	private String email;
	
	@Column(nullable = false)
	@Size(min = 13, max = 13)
	private String pmb;
	
	public AgentUser() {
		
	}
	
	public AgentUser(Long id, User user, String email, String pmb) {
		super();
		this.id = id;
		this.email = email;
		this.pmb = pmb;
	}

	public AgentUser(User user, String email, String pmb) {
		super();
		this.email = email;
		this.pmb = pmb;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPmb() {
		return pmb;
	}

	public void setPmb(String pmb) {
		this.pmb = pmb;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
