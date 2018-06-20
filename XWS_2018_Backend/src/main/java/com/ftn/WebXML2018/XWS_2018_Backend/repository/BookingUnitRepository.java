package com.ftn.WebXML2018.XWS_2018_Backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationType;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.MonthlyPrices;

public interface BookingUnitRepository extends JpaRepository<BookingUnit,Long>{

	@Query(value="select mp from MonthlyPrices mp INNER JOIN mp.bookingUnit bu WHERE bu.city = ?1 AND bu.peopleNumber >= ?2 AND mp.month = ?3 AND mp.year = ?4")
	public Page<MonthlyPrices> findBookingUnitsByCity(City city, int peopleNumber, int month, int year, Pageable pageable);
	
	@Query("select mp from MonthlyPrices mp INNER JOIN mp.bookingUnit bu INNER JOIN bu.city c WHERE c.country = ?1 AND bu.peopleNumber >= ?2 AND mp.month = ?3 AND mp.year = ?4")
	public Page<MonthlyPrices> findBookingUnitsByCountry(Country country, int peopleNumber, int month, int year, Pageable pageable);
	
	@Query("select mp from MonthlyPrices mp INNER JOIN mp.bookingUnit bu WHERE bu.city = ?1 AND bu.peopleNumber >= ?2 AND mp.month = ?3 AND mp.year = ?4 order by mp.amount DESC")
	public Page<MonthlyPrices> findBookingUnitsByCityOrderByPriceDescending(City city, int peopleNumber, int month, int year, Pageable pageable);
	
	@Query("select mp from MonthlyPrices mp INNER JOIN mp.bookingUnit bu INNER JOIN bu.city c WHERE c.country = ?1 AND bu.peopleNumber >= ?2 AND mp.month = ?3 AND mp.year = ?4 order by mp.amount DESC")
	public Page<MonthlyPrices> findBookingUnitsByCountryOrderByPriceDescending(Country country, int peopleNumber, int month, int year, Pageable pageable);
	
	@Query("select mp from MonthlyPrices mp INNER JOIN mp.bookingUnit bu WHERE bu.city = ?1 AND bu.peopleNumber >= ?2 AND mp.month = ?3 AND mp.year = ?4 order by mp.amount ASC")
	public Page<MonthlyPrices> findBookingUnitsByCityOrderByPriceAscending(City city, int peopleNumber, int month, int year, Pageable pageable);
	
	@Query("select mp from MonthlyPrices mp INNER JOIN mp.bookingUnit bu INNER JOIN bu.city c WHERE c.country = ?1 AND bu.peopleNumber >= ?2 AND mp.month = ?3 AND mp.year = ?4 order by mp.amount ASC")
	public Page<MonthlyPrices> findBookingUnitsByCountryOrderByPriceAscending(Country country, int peopleNumber, int month, int year, Pageable pageable);
	
}
