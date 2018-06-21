package com.ftn.WebXML2018.XWS_2018_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.MonthlyPrices;

public interface MonthlyPricesRepository extends JpaRepository<MonthlyPrices,Long>{

	public MonthlyPrices findOneByBookingUnitAndMonthAndYear(BookingUnit bookingUnit, int month, int year);
		
	public MonthlyPrices findOneByBookingUnitIdAndMonthAndYear(Long id, int month, int year);
	
}
