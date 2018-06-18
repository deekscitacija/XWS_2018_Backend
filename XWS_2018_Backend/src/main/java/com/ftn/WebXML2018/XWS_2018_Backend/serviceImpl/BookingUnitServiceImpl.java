package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.MonthlyPrices;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.BookingUnitRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BookingUnitService;

@Service
public class BookingUnitServiceImpl implements BookingUnitService {

	@Autowired
	private BookingUnitRepository bookingUnitRepository;
	
	@Override
	public Page<MonthlyPrices> findBookingUnitsByCity(City city, int peopleNumber, Pageable pageable) {
		
		Calendar calendar = Calendar.getInstance();
		
		return bookingUnitRepository.findBookingUnitsByCity(city, peopleNumber, calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR), pageable);
	}

	@Override
	public Page<MonthlyPrices> findBookingUnitsByCountry(Country country, int peopleNumber,Pageable pageable) {

		Calendar calendar = Calendar.getInstance();
		
		return bookingUnitRepository.findBookingUnitsByCountry(country, peopleNumber, calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR), pageable);
	}

	@Override
	public BookingUnit findById(Long id) {
		
		return bookingUnitRepository.findOne(id);
	}

}
