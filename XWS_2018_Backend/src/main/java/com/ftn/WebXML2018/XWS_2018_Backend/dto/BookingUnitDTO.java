package com.ftn.WebXML2018.XWS_2018_Backend.dto;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;

public class BookingUnitDTO {

	private BookingUnit bookingUnit;
	private boolean reserved;
	
	public BookingUnitDTO() {}
	
	public BookingUnitDTO(BookingUnit bookingUnit, boolean reserved) {
		super();
		this.bookingUnit = bookingUnit;
		this.reserved = reserved;
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

	@Override
	public String toString() {
		return "BookingUnitDTO [bookingUnit=" + bookingUnit + ", reserved=" + reserved + "]";
	}
}
