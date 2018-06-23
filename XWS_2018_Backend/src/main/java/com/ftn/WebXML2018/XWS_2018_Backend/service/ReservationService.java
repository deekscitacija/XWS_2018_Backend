package com.ftn.WebXML2018.XWS_2018_Backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.RegisteredUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation;
import com.ftn.WebXML2018.XWS_2018_Backend.enums.ReservationStatus;
import com.ftn.WebXML2018.XWS_2018_Backend.exceptions.ReservationAlredyExsistsException;

public interface ReservationService {

	public Page<Reservation> findReservationsByUser(RegisteredUser user, Pageable pageable);
	
	public Page<Reservation> findByRegisteredUserAndConfirmed(RegisteredUser registeredUser, ReservationStatus reservationStatus, Pageable pageable);

	public Reservation saveReservation(Reservation reservation) throws ReservationAlredyExsistsException;
	
	public List<Reservation> findCriticalReservations(BookingUnit bookingUnit, Date startDate, Date endDate);
	
	public Reservation findById(Long id);
	
	public void deleteReservation(Long id);
	
	public Reservation cancelReservation(Reservation reservation);
	
	public List<Reservation> findReservationsByBookingUnit(BookingUnit bookingUnit);
}
