package com.ftn.WebXML2018.XWS_2018_Backend.agentDto;

public class MonthlyPrices_DTO {

	private Long bookingUnitMainServerId;
	
	private int year;
	
	private double[] monthlyPrices;

	public MonthlyPrices_DTO(Long bookingUnitMainServerId, int year, double[] monthlyPrices) {
		super();
		this.bookingUnitMainServerId = bookingUnitMainServerId;
		this.year = year;
		this.monthlyPrices = monthlyPrices;
	}

	public Long getBookingUnitMainServerId() {
		return bookingUnitMainServerId;
	}

	public void setBookingUnitMainServerId(Long bookingUnitMainServerId) {
		this.bookingUnitMainServerId = bookingUnitMainServerId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double[] getMonthlyPrices() {
		return monthlyPrices;
	}

	public void setMonthlyPrices(double[] monthlyPrices) {
		this.monthlyPrices = monthlyPrices;
	}	
}
