package com.ftn.WebXML2018.XWS_2018_Backend.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;

public interface BookingUnitService {

	public Page<BookingUnit> findBookingUnitsByCity(City city, int peopleNumber, Date dateFrom, Date dateTo, Pageable pageable);
	public Page<BookingUnit> findBookingUnitsByCountry(Country country, int peopleNumber, Date dateFrom, Date dateTo, Pageable pageable);
}
