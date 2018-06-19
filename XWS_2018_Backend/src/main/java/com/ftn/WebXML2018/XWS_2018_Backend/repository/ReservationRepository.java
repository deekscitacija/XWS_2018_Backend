package com.ftn.WebXML2018.XWS_2018_Backend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.RegisteredUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	public Page<Reservation> findByRegisteredUser(RegisteredUser registeredUser, Pageable pageable);
	
	public Page<Reservation> findByRegisteredUserAndConfirmedOrderByFromDateDesc(RegisteredUser registeredUser, boolean confirmed, Pageable pageable);

	@Query("from Reservation as r where r.bookingUnit = ?1 and ((r.fromDate <= ?2 and r.toDate >= ?2 and r.toDate <= ?3) or (r.fromDate >= ?2  and r.toDate <= ?3) or (r.fromDate >= ?2 and r.fromDate <= ?3 and r.toDate >= ?3) or (r.fromDate <= ?2 and r.toDate >= ?3))")
	public List<Reservation> findCriticalReservations(BookingUnit bookingUnit, Date startDate, Date endDate);
}
