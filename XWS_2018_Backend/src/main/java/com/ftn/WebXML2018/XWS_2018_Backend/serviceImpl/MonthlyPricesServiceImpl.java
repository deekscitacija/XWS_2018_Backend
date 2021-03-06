package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.BookingUnitDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.MonthlyPrices;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.MonthlyPricesRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.ReservationRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.service.MonthlyPricesService;

@Service
public class MonthlyPricesServiceImpl implements MonthlyPricesService {

	@Autowired
	private MonthlyPricesRepository monthlyPricesRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public MonthlyPrices findOneByBookingUnitAndMonthAndYear(BookingUnit bookingUnit, int month, int year) {
		return monthlyPricesRepository.findOneByBookingUnitAndMonthAndYear(bookingUnit, month, year);
	}

	@Override
	public double calculateTotalPrice(BookingUnit bookingUnit, Date fromDate, Date toDate) {

		int dana = Days.daysBetween(new LocalDate(fromDate), new LocalDate(toDate)).getDays();
	    
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()));
	    
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		
		MonthlyPrices monthlyPrice = monthlyPricesRepository.findOneByBookingUnitAndMonthAndYear(bookingUnit, month+1, year);
		
		if(monthlyPrice == null) {
			return -1.00;
		}
		
		return monthlyPrice.getAmount() * dana;

	}

	@Override
	public BookingUnitDTO findByBookingUnitId(Long id) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()));
	    
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		
		MonthlyPrices monthlyPrice = monthlyPricesRepository.findOneByBookingUnitIdAndMonthAndYear(id, month+1, year);
		
		if(monthlyPrice==null) {
			return null;
		}
		
		return convertToBookingUnitDTO(monthlyPrice);
	}

	private BookingUnitDTO convertToBookingUnitDTO(MonthlyPrices monthlyPrice) {
		
		boolean reserved = false;
		BookingUnit bookingUnit = monthlyPrice.getBookingUnit();
			
		return new BookingUnitDTO(bookingUnit, reserved, monthlyPrice.getAmount(), new Double(0));	
	}

	@Override
	public MonthlyPrices insertMonthlyPrices(MonthlyPrices monthlyPrices) {
		return monthlyPricesRepository.save(monthlyPrices);
	}

}
