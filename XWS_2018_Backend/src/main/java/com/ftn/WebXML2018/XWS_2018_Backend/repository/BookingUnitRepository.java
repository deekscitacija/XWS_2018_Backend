package com.ftn.WebXML2018.XWS_2018_Backend.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;

public interface BookingUnitRepository extends JpaRepository<BookingUnit,Long>{
	
	@Query("select bu from BookingUnit bu INNER JOIN bu.reservations r WHERE bu.city = ?1 AND bu.peopleNumber >= ?2 AND r.fromDate NOT BETWEEN ?3 AND ?4 AND r.toDate NOT BETWEEN ?3 AND ?4")
	public Page<BookingUnit> findBookingUnitsByCity(City city, int peopleNumber, Date dateFrom, Date dateTo, Pageable pageable);
	
	@Query("select bu from BookingUnit bu INNER JOIN bu.reservations r INNER JOIN bu.city c WHERE c.country = ?1 AND bu.peopleNumber >= ?2 AND r.fromDate NOT BETWEEN ?3 AND ?4 AND r.toDate NOT BETWEEN ?3 AND ?4")
	public Page<BookingUnit> findBookingUnitsByCountry(Country country, int peopleNumber, Date dateFrom, Date dateTo, Pageable pageable);
}
