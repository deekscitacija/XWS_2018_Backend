package com.ftn.WebXML2018.XWS_2018_Backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.BookingUnitDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationCategory;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationType;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BonusFeatures;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;

public interface BookingUnitService {

	public Page<BookingUnitDTO> findBookingUnits(City city, Country country, int peopleNumber, Date startDate, Date endDate, List<AccomodationType> accomodationTypes, List<AccomodationCategory> accomodationCategories, List<BonusFeatures> bonusFeatures, Pageable pageable);
	
	public BookingUnit findById(Long id);
	
	public BookingUnit insertBookingUnit(BookingUnit bookingUnit);
	
}
