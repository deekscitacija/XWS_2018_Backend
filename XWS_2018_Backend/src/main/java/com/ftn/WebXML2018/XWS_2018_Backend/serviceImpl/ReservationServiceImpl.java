package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.RegisteredUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.ReservationRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public Page<Reservation> findReservationsByUser(RegisteredUser user, Pageable pageable) {
		
		return reservationRepository.findByRegisteredUser(user, pageable);
	}

	@Override
	public Page<Reservation> findByRegisteredUserAndConfirmed(RegisteredUser registeredUser, boolean confirmed,
			Pageable pageable) {
		
		return reservationRepository.findByRegisteredUserAndConfirmed(registeredUser, confirmed, pageable);
	}

}
