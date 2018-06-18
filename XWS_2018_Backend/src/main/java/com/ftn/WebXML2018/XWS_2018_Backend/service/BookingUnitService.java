package com.ftn.WebXML2018.XWS_2018_Backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.MonthlyPrices;

public interface BookingUnitService {

	public Page<MonthlyPrices> findBookingUnitsByCity(City city, int peopleNumber, Pageable pageable);
	
	public Page<MonthlyPrices> findBookingUnitsByCountry(Country country, int peopleNumber, Pageable pageable);
	
	public BookingUnit findById(Long id);
}
