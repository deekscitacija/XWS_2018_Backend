package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnitPicture;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.BookingUnitPictureRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BookingUnitPictureService;

public class BookingUnitPictureServiceImpl implements BookingUnitPictureService {

	@Autowired
	private BookingUnitPictureRepository bookingUnitPictureRepository;
	
	@Override
	public List<BookingUnitPicture> findAllByBookingUnit(BookingUnit bookingUnit) {
		return bookingUnitPictureRepository.findAllByBookingUnit(bookingUnit);
	}

	@Override
	public List<BookingUnitPicture> findOneByBookingUnit(BookingUnit bookingUnit) {
		return bookingUnitPictureRepository.findOneByBookingUnit(bookingUnit);
	}

	@Override
	public BookingUnitPicture insertBookingUnitPicture(BookingUnitPicture bookingUnitPicture) {
		return bookingUnitPictureRepository.save(bookingUnitPicture);
	}

}
