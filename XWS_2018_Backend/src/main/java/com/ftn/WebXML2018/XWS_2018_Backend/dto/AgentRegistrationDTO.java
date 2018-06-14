package com.ftn.WebXML2018.XWS_2018_Backend.dto;

public class AgentRegistrationDTO {

	private String username;
	private String email;
	private String password;
	private String name;
	private String surname;
	private String phoneNumber;
	private String city;
	private String state;
	private String homeAddress;
	private String pmb;
	private String postCode;
	
	public AgentRegistrationDTO(String username, String email, String password, String name, String surname,
			String phoneNumber, String city, String state, String homeAddress, String pmb, String postCode) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.state = state;
		this.homeAddress = homeAddress;
		this.pmb = pmb;
	}
	
	public AgentRegistrationDTO() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getPmb() {
		return pmb;
	}
	public void setPmb(String pmb) {
		this.pmb = pmb;
	}
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
}
