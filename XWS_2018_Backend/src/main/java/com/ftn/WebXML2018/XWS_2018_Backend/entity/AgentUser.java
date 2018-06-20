package com.ftn.WebXML2018.XWS_2018_Backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class AgentUser {

	@Id
	private Long id;
	
	@Column(nullable = false)
	@Size(min = 13, max = 13)
	private String pmb;
	
	public AgentUser() {
		
	}
	
	public AgentUser(Long id, String pmb) {
		super();
		this.id = id;
		this.pmb = pmb;
	}

	public AgentUser(String pmb) {
		super();
		this.pmb = pmb;
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
