package com.ftn.WebXML2018.XWS_2018_Backend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PasswordResetToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String token;
	
	@ManyToOne(optional = false)
	private User user;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
	
	public PasswordResetToken() {
		
	}

	public PasswordResetToken(Long id, String token, User user, Date expirationDate) {
		super();
		this.id = id;
		this.token = token;
		this.user = user;
		this.expirationDate = expirationDate;
	}
	
	public PasswordResetToken(String token, User user, Date expirationDate) {
		super();
		this.token = token;
		this.user = user;
		this.expirationDate = expirationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
}
