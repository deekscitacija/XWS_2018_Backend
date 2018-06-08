package com.ftn.WebXML2018.XWS_2018_Backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ftn.WebXML2018.XWS_2018_Backend.enums.UserRolesType;

@Entity
public class UserRoles {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private UserRolesType name;
	
	public UserRoles() {
		
	}

	public UserRoles(Long id, UserRolesType name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public UserRoles(UserRolesType name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserRolesType getName() {
		return name;
	}

	public void setName(UserRolesType name) {
		this.name = name;
	}
	
}
