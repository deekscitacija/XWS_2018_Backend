package com.ftn.WebXML2018.XWS_2018_Backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.RegisteredUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation;
import com.ftn.WebXML2018.XWS_2018_Backend.exceptions.ReservationAlredyExsistsException;

public interface ReservationService {

	public Page<Reservation> findReservationsByUser(RegisteredUser user, Pageable pageable);
	
	public Page<Reservation> findByRegisteredUserAndConfirmed(RegisteredUser registeredUser, boolean confirmed, Pageable pageable);

	public Reservation saveReservation(Reservation reservation) throws ReservationAlredyExsistsException;
	
	public List<Reservation> findCriticalReservations(BookingUnit bookingUnit, Date startDate, Date endDate);
}
