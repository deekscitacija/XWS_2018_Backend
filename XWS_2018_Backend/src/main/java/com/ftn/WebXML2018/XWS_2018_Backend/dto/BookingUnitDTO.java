package com.ftn.WebXML2018.XWS_2018_Backend.dto;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;

public class BookingUnitDTO {

	private BookingUnit bookingUnit;
	private boolean reserved;
	private Double price;
	private Double rating;
	
	public BookingUnitDTO() {}
	
	public BookingUnitDTO(BookingUnit bookingUnit, boolean reserved, Double price, Double rating) {
		super();
		this.bookingUnit = bookingUnit;
		this.reserved = reserved;
		this.price = price;
		this.rating = rating;
	}

	public BookingUnit getBookingUnit() {
		return bookingUnit;
	}

	public void setBookingUnit(BookingUnit bookingUnit) {
		this.bookingUnit = bookingUnit;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "BookingUnitDTO [bookingUnit=" + bookingUnit + ", reserved=" + reserved + ", price=" + price
				+ ", rating=" + rating + "]";
	}
}
