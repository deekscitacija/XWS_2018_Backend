package com.ftn.WebXML2018.XWS_2018_Backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.RegisteredUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	public Page<Reservation> findByRegisteredUser(RegisteredUser registeredUser, Pageable pageable);
	
	public Page<Reservation> findByRegisteredUserAndConfirmed(RegisteredUser registeredUser, boolean confirmed, Pageable pageable);

}
