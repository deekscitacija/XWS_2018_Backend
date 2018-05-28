package com.ftn.WebXML2018.XWS_2018_Backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class BookingUnitPicture {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Size(max = 1000)
	private String value;
	
	@ManyToOne(optional = false)
	@JsonBackReference
	private BookingUnit bookingUnit;
	
	public BookingUnitPicture() {
		
	}

	public BookingUnitPicture(Long id, String value, BookingUnit bookingUnit) {
		super();
		this.id = id;
		this.value = value;
		this.bookingUnit = bookingUnit;
	}
	
	public BookingUnitPicture(String value, BookingUnit bookingUnit) {
		super();
		this.value = value;
		this.bookingUnit = bookingUnit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public BookingUnit getBookingUnit() {
		return bookingUnit;
	}

	public void setBookingUnit(BookingUnit bookingUnit) {
		this.bookingUnit = bookingUnit;
	}

}
