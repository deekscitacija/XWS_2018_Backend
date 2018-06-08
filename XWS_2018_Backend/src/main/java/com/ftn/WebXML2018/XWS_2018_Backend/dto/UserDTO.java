package com.ftn.WebXML2018.XWS_2018_Backend.dto;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.UserRoles;

public class UserDTO {
	
	private Long id;
	private String username;
	private UserRoles userRole;
	
	public UserDTO() {
		
	}

	public UserDTO(Long id, String username, UserRoles userRole) {
		super();
		this.id = id;
		this.username = username;
		this.userRole = userRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserRoles getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoles userRole) {
		this.userRole = userRole;
	}

}
