package com.ftn.WebXML2018.XWS_2018_Backend.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;

public interface BookingUnitRepository extends JpaRepository<BookingUnit,Long>{
	
	@Query("select DISTINCT bu from BookingUnit bu INNER JOIN bu.reservations r WHERE bu.city = ?1 AND bu.peopleNumber >= ?2")
	public Page<BookingUnit> findBookingUnitsByCity(City city, int peopleNumber, Pageable pageable);
	
	@Query("select DISTINCT bu from BookingUnit bu INNER JOIN bu.reservations r INNER JOIN bu.city c WHERE c.country = ?1 AND bu.peopleNumber >= ?2")
	public Page<BookingUnit> findBookingUnitsByCountry(Country country, int peopleNumber, Pageable pageable);
}
