package com.ftn.WebXML2018.XWS_2018_Backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.RegisteredUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation;

public interface ReservationService {

	public Page<Reservation> findReservationsByUser(RegisteredUser user, Pageable pageable);
	
	public Page<Reservation> findByRegisteredUserAndConfirmed(RegisteredUser registeredUser, boolean confirmed, Pageable pageable);
	
}
