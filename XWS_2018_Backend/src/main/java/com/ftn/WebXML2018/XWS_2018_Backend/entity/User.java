package com.ftn.WebXML2018.XWS_2018_Backend.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(nullable = false, unique = true)
	@Size(max = 60)
	private String username;
	
	@Column(nullable = false)
	@Size(max = 90)
	private String password;
	
	@Column(nullable = false)
	@Size(max = 60)
	private String name;
	
	@Column(nullable = false)
	@Size(max = 60)
	private String surname;
	
	@ManyToOne(optional = false)
	private UserRoles userRole;
	
	@ManyToOne(optional = false)
	private City city;
	
	@Column(nullable = true)
	@Size(max = 30)
	private String homeAddress;
	
	@OneToOne(optional = true)
    @PrimaryKeyJoinColumn
    private AgentUser agentUser;
	
	@OneToOne(optional = true)
    @PrimaryKeyJoinColumn
    private RegisteredUser registeredUser;
	
	public User() {
		
	}

	public User(Long id, String username, String password, String name, String surname, UserRoles userRole, 
			City city, RegisteredUser registeredUser, AgentUser agentUser, String homeAddress) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.userRole = userRole;
		this.city = city;
		this.registeredUser = registeredUser;
		this.agentUser = agentUser;
		this.homeAddress = homeAddress;
	}
	
	public User(String username, String password, String name, String surname, UserRoles userRole, City city, String homeAddress) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.userRole = userRole;
		this.city = city;
		this.homeAddress = homeAddress;
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

	public UserRoles getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoles userRole) {
		this.userRole = userRole;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public AgentUser getAgentUser() {
		return agentUser;
	}

	public void setAgentUser(AgentUser agentUser) {
		this.agentUser = agentUser;
	}

	public RegisteredUser getRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
	}

}
