package com.ftn.WebXML2018.XWS_2018_Backend.dto;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.RegisteredUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.UserRoles;

public class UserDTO {
	
	private Long id;
	private String username;
	private UserRoles userRole;
	private String name;
	private String surname;
	private String homeAddress;
	private RegisteredUser registeredUser;
	private City city;
	private String email;
	
	public UserDTO() {
		
	}

	public UserDTO(Long id, String username, String password, UserRoles userRole, String name, String surname, String homeAddress, 
			RegisteredUser registeredUser, City city, String email) {
		super();
		this.id = id;
		this.username = username;
		this.userRole = userRole;
		this.name = name;
		this.surname = surname;
		this.homeAddress = homeAddress;
		this.registeredUser = registeredUser;
		this.city = city;
		this.email = email;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public RegisteredUser getRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
