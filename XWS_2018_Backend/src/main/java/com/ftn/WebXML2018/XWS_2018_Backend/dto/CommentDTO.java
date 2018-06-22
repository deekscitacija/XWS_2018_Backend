package com.ftn.WebXML2018.XWS_2018_Backend.dto;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation;

public class CommentDTO {

	private Reservation reservation;
	private String content;
	private String status;
	
	public CommentDTO() {}
	
	public CommentDTO(Reservation reservation, String content, String status) {
		super();
		this.reservation = reservation;
		this.content = content;
		this.status = status;
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
	
}
