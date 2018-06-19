package com.ftn.WebXML2018.XWS_2018_Backend.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.BookingUnitDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.MonthlyPrices;

public interface BookingUnitService {

	public Page<BookingUnitDTO> findBookingUnits(City city, Country country, int peopleNumber, Date startDate, Date endDate, String mode, Pageable pageable);
	
	public BookingUnit findById(Long id);
}
