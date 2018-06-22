package com.ftn.WebXML2018.XWS_2018_Backend.dto;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;

public class CommentDTO {
	
	private Reservation reservation;
	private User user;
	private String content;
	private String status;
	
	public CommentDTO() {}
	
	public CommentDTO(User user, Reservation reservation, String content, String status) {
		super();
		this.reservation = reservation;
		this.content = content;
		this.status = status;
		this.user = user;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
