package com.ftn.WebXML2018.XWS_2018_Backend.dto;

public class AgentUserDTO {
	private Long id;
	private String username;
	private String email;
	private String name;
	private String surname;
	private String phoneNumber;
	private String city;
	private String state;
	private String homeAddress;
	private String pmb;
	
	public AgentUserDTO() {
		super();
	}
	public AgentUserDTO(String username, String email, String name, String surname, String phoneNumber, String city,
			String state, String homeAddress, String pmb) {
		super();
		this.username = username;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.state = state;
		this.homeAddress = homeAddress;
		this.pmb = pmb;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
