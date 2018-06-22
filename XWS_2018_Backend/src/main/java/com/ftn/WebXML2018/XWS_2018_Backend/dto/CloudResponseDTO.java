package com.ftn.WebXML2018.XWS_2018_Backend.dto;

public class CloudResponseDTO {
	
	private long booking_unit_id;
	private long reservation_id;
	private String comment;
	private double rating;
	private String commentStatus;
	
	public CloudResponseDTO() {
		
	}

	public CloudResponseDTO(long booking_unit_id, long reservation_id, String comment, double rating,
			String commentStatus) {
		super();
		this.booking_unit_id = booking_unit_id;
		this.reservation_id = reservation_id;
		this.comment = comment;
		this.rating = rating;
		this.commentStatus = commentStatus;
	}

	public long getBooking_unit_id() {
		return booking_unit_id;
	}

	public void setBooking_unit_id(long booking_unit_id) {
		this.booking_unit_id = booking_unit_id;
	}

	public long getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(long reservation_id) {
		this.reservation_id = reservation_id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}

	@Override
	public String toString() {
		return "CloudResponseDTO [booking_unit_id=" + booking_unit_id + ", reservation_id=" + reservation_id
				+ ", comment=" + comment + ", rating=" + rating + ", commentStatus=" + commentStatus + "]";
	}
	
	

}
