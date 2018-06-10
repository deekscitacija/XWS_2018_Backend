package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.MonthlyPrices;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.MonthlyPricesRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.service.MonthlyPricesService;

@Service
public class MonthlyPricesServiceImpl implements MonthlyPricesService {

	@Autowired
	private MonthlyPricesRepository monthlyPricesRepository;
	
	@Override
	public MonthlyPrices findOneByBookingUnitAndMonthAndYear(BookingUnit bookingUnit, int month, int year) {
		return monthlyPricesRepository.findOneByBookingUnitAndMonthAndYear(bookingUnit, month, year);
	}

}
