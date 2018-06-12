package com.ftn.WebXML2018.XWS_2018_Backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnitPicture;

@Service
public interface BookingUnitPictureService {

	public List<BookingUnitPicture> findAllByBookingUnit(BookingUnit bookingUnit);
	public List<BookingUnitPicture> findOneByBookingUnit(BookingUnit bookingUnit);
	
}