package com.ftn.WebXML2018.XWS_2018_Backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(uniqueConstraints= @UniqueConstraint(columnNames = {"booking_unit_id","month", "year"}))
public class MonthlyPrices {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Min(0)
	private double amount;
	
	@Column(nullable = false)
	@Min(1)
	@Max(12)
	private int month;
	
	@Column(nullable = false)
	@Min(2000)
	private int year;
	
	@ManyToOne(optional = false)
	private BookingUnit bookingUnit;
	
	public MonthlyPrices() {
		
	}

	public MonthlyPrices(Long id, double amount, int month, int year, BookingUnit bookingUnit) {
		super();
		this.id = id;
		this.amount = amount;
		this.month = month;
		this.year = year;
		this.bookingUnit = bookingUnit;
	}
	
	public MonthlyPrices(double amount, int month, int year, BookingUnit bookingUnit) {
		super();
		this.amount = amount;
		this.month = month;
		this.year = year;
		this.bookingUnit = bookingUnit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public BookingUnit getBookingUnit() {
		return bookingUnit;
	}

	public void setBookingUnit(BookingUnit bookingUnit) {
		this.bookingUnit = bookingUnit;
	}	

}
