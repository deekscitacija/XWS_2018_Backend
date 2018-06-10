package com.ftn.WebXML2018.XWS_2018_Backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnitPicture;

public interface BookingUnitPictureRepository extends JpaRepository<BookingUnitPicture,Long>{

	public List<BookingUnitPicture> findAllByBookingUnit(BookingUnit bookingUnit);
	public List<BookingUnitPicture> findOneByBookingUnit(BookingUnit bookingUnit);
}
