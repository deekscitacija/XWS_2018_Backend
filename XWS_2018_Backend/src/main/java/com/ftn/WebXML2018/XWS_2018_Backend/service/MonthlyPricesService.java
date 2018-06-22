package com.ftn.WebXML2018.XWS_2018_Backend.service;

import java.util.Date;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.BookingUnitDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.MonthlyPrices;

public interface MonthlyPricesService {

	public MonthlyPrices findOneByBookingUnitAndMonthAndYear(BookingUnit bookingUnit, int month, int year);
	
	public double calculateTotalPrice(BookingUnit bookingUnit, Date fromDate, Date toDate);
	
	public BookingUnitDTO findByBookingUnitId(Long id);
	
}
