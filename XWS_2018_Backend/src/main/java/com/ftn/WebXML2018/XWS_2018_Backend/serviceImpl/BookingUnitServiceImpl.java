package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.BookingUnitDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationCategory;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationType;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BonusFeatures;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.MonthlyPrices;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation;
import com.ftn.WebXML2018.XWS_2018_Backend.enums.SortModes;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.BookingUnitRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.ReservationRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BookingUnitService;

@Service
public class BookingUnitServiceImpl implements BookingUnitService {

	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private BookingUnitRepository bookingUnitRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public Page<BookingUnitDTO> findBookingUnits(City city, Country country, int peopleNumber, Date startDate, Date endDate, List<AccomodationType> accomodationTypes, List<AccomodationCategory> accomodationCategories, List<BonusFeatures> bonusFeatures, Pageable pageable) {
		
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<MonthlyPrices> query = builder.createQuery(MonthlyPrices.class);
		Root<MonthlyPrices> root = query.from(MonthlyPrices.class);
		root.alias("monthlyPrices");
		
		Predicate thisMonth = builder.equal(root.get("month"), month);
		Predicate thisYear = builder.equal(root.get("year"), year);
		
		Join<MonthlyPrices,BookingUnit> bookingUnitJoin = root.join("bookingUnit");
		bookingUnitJoin.alias("monthlyPricesBookingUnitJoin");	
		Predicate enoughPeople = builder.ge(bookingUnitJoin.get("peopleNumber"), peopleNumber);
		
		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
		Root<MonthlyPrices> countRoot = countQuery.from(query.getResultType());
		countRoot.alias("monthlyPrices");
		Join<MonthlyPrices,BookingUnit> countbookingUnitJoin = countRoot.join("bookingUnit");
		countbookingUnitJoin.alias("monthlyPricesBookingUnitJoin");
		
		Predicate necessary = builder.and(builder.and(thisMonth,thisYear,enoughPeople));
		
		Predicate destinationType = null;
		
		if(city!=null) {
			destinationType = builder.equal(bookingUnitJoin.get("city"), city);
		}else if(country!=null) {
			Join<BookingUnit,City> cityJoin = bookingUnitJoin.join("city");
			cityJoin.alias("bookingUnitCityJoin");
			Join<BookingUnit,City> countCityJoin = countbookingUnitJoin.join("city");
			countCityJoin.alias("bookingUnitCityJoin");
			destinationType = builder.equal(countCityJoin.get("country"), country);
		}
		
		Predicate withDestination = builder.and(necessary,destinationType);
		
		List<Predicate> advanced = new ArrayList<Predicate>();
		
		advanced.add(withDestination);
				
		if(accomodationTypes!=null) {
			if(!accomodationTypes.isEmpty()) {
				Expression<AccomodationType> accomodationTypeExpression = bookingUnitJoin.get("accomodationType");
				Predicate accomodationTypePredicate = accomodationTypeExpression.in(accomodationTypes);
				
				advanced.add(accomodationTypePredicate);
			}	
		}
		
		if(accomodationCategories!=null) {
			if(!accomodationCategories.isEmpty()) {
				Expression<AccomodationCategory> accomodationCategoryExpression = bookingUnitJoin.get("accomodationCategory");
				Predicate accomodationCategoryPredicate = accomodationCategoryExpression.in(accomodationCategories);
				
				advanced.add(accomodationCategoryPredicate);
			}		
		}
		
		if(bonusFeatures!=null) {
			if(!bonusFeatures.isEmpty()) {
				Expression<Collection<BonusFeatures>> bonusFeaturesExpression = bookingUnitJoin.get("bonusFeatures");
				for(BonusFeatures bonusFeature : bonusFeatures) {
					Predicate bonusFeaturesPredicate = builder.isMember(bonusFeature, bonusFeaturesExpression);
					
					advanced.add(bonusFeaturesPredicate);
				}	
			}
		}
			
		query.where(builder.and(advanced.toArray(new Predicate[advanced.size()])));
		query.select(root);
		query.orderBy(builder.asc(bookingUnitJoin.get("name")));
		
		List<MonthlyPrices> monthlyPrices = em.createQuery(query)
				.setMaxResults(pageable.getPageSize())
				.setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
				.getResultList();
		
				
		countQuery.select(builder.count(countRoot));
		
		Predicate restriction = query.getRestriction();
		if (restriction != null) {
		  countQuery.where(restriction);
		}
		
		Long count = em.createQuery(countQuery).getSingleResult();
		
		return new PageImpl<>(convertToBookingUnitDTO(monthlyPrices,startDate,endDate),pageable,count);
	}

	@Override
	public BookingUnit findById(Long id) {
		
		return bookingUnitRepository.findOne(id);
	}
	
	
	private List<BookingUnitDTO> convertToBookingUnitDTO(List<MonthlyPrices> monthlyPrices, Date startDate, Date endDate) {
			
		List<BookingUnitDTO> bookingUnits = new ArrayList<BookingUnitDTO>();
		for(MonthlyPrices monthlyPrice : monthlyPrices) {
			boolean reserved = false;
			BookingUnit bookingUnit = monthlyPrice.getBookingUnit();
			List<Reservation> reservations = reservationRepository.findCriticalReservations(bookingUnit, startDate, endDate);
			if(!reservations.isEmpty())
				reserved = true;
			
			bookingUnits.add(new BookingUnitDTO(bookingUnit,reserved,monthlyPrice.getAmount(),null));
		}
		
		return bookingUnits;
	}
	

}
