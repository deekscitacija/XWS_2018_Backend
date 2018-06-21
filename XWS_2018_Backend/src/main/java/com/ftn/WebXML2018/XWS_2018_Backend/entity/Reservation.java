package com.ftn.WebXML2018.XWS_2018_Backend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.ftn.WebXML2018.XWS_2018_Backend.enums.ReservationStatus;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fromDate;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date toDate;
	
	@Column(nullable = false)
	private double totalPrice;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ReservationStatus reservationStatus = ReservationStatus.WAITING;
	
	@Column(nullable = false)
	@Size(max = 60)
	private String subjectName;
	
	@Column(nullable = false)
	@Size(max = 60)
	private String subjectSurname;
	
	@ManyToOne(optional = true)
	private RegisteredUser registeredUser;
	
	@ManyToOne(optional = false)
	private BookingUnit bookingUnit;
	
	public Reservation() {
		
	}

	public Reservation(Long id, Date fromDate, Date toDate, double totalPrice, ReservationStatus reservationStatus, 
			String subjectName, String subjectSurname, RegisteredUser registeredUser, BookingUnit bookingUnit) {
		super();
		this.id = id;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.totalPrice = totalPrice;
		this.reservationStatus = reservationStatus;
		this.subjectName = subjectName;
		this.subjectSurname = subjectSurname;
		this.registeredUser = registeredUser;
		this.bookingUnit = bookingUnit;
	}
	
	public Reservation(Date fromDate, Date toDate, double totalPrice, ReservationStatus reservationStatus,
			String subjectName, String subjectSurname, RegisteredUser registeredUser, BookingUnit bookingUnit) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.totalPrice = totalPrice;
		this.reservationStatus = reservationStatus;
		this.subjectName = subjectName;
		this.subjectSurname = subjectSurname;
		this.registeredUser = registeredUser;
		this.bookingUnit = bookingUnit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectSurname() {
		return subjectSurname;
	}

	public void setSubjectSurname(String subjectSurname) {
		this.subjectSurname = subjectSurname;
	}

	public RegisteredUser getRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
	}

	public BookingUnit getBookingUnit() {
		return bookingUnit;
	}

	public void setBookingUnit(BookingUnit bookingUnit) {
		this.bookingUnit = bookingUnit;
	}

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", fromDate=" + fromDate + ", toDate=" + toDate + ", totalPrice=" + totalPrice
				+ ", reservationStatus=" + reservationStatus + ", subjectName=" + subjectName + ", subjectSurname="
				+ subjectSurname + ", registeredUser=" + registeredUser + ", bookingUnit=" + bookingUnit + "]";
	}

}
