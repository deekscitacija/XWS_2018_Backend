package com.ftn.WebXML2018.XWS_2018_Backend.helpClasses;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.AgentUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BonusFeatures;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.RegisteredUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl.MonthlyPricesServiceImpl;
import com.ftn_booking.agentendpoint.AccomodationCategory;
import com.ftn_booking.agentendpoint.AccomodationType;
import com.ftn_booking.agentendpoint.BonusFeature;
import com.ftn_booking.agentendpoint.City;
import com.ftn_booking.agentendpoint.Country;
import com.ftn_booking.agentendpoint.MainServerReservation;
import com.ftn_booking.agentendpoint.Message;
import com.ftn_booking.agentendpoint.RegUsrMessage;
import com.ftn_booking.agentendpoint.RegisteredUserInfo;
import com.ftn_booking.agentendpoint.Reservation;
import com.ftn_booking.agentendpoint.ReservationStatus;

public class Entity2SoapConverter {

	private MonthlyPricesServiceImpl monthlyService = new MonthlyPricesServiceImpl();
	
	public static Country convertCountry(com.ftn.WebXML2018.XWS_2018_Backend.entity.Country country) {
		Country c = new Country();
		c.setMainServerId(country.getId());
		c.setName(country.getName());
		
		return c;
	}
	
	public static City convertCity(com.ftn.WebXML2018.XWS_2018_Backend.entity.City city) {
		City c = new City();
		c.setMainServerId(city.getId());
		c.setName(city.getName());
		c.setPostalCode(city.getPostcode());
		c.setCountry(convertCountry(city.getCountry()));
		
		return c;
	}
	
	public static AccomodationCategory convertCategory(com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationCategory category) {
		AccomodationCategory c = new AccomodationCategory();
		c.setName(category.getName());
		c.setMainServerId(category.getId());
		
		return c;
	}
	
	public static AccomodationType convertType(com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationType type) {
		AccomodationType t = new AccomodationType();
		t.setMainServerId(type.getId());
		t.setName(type.getName());
		
		return t;
	}
	
	public static BonusFeature convertBonus(BonusFeatures bonus) {
		BonusFeature b = new BonusFeature();
		b.setMainServerId(bonus.getId());
		b.setName(bonus.getName());
		
		return b;
	}
	
	public static RegisteredUserInfo convertRegisterUser(User regUser) {
		RegisteredUserInfo ret = new RegisteredUserInfo();
		ret.setMainServerId(regUser.getId());
		ret.setUserName(regUser.getUsername());
		
		return ret;
	}
	
	public static RegUsrMessage convertMessage(com.ftn.WebXML2018.XWS_2018_Backend.entity.Message message, AgentUser agent) {
		RegUsrMessage m = new RegUsrMessage();
		m.setContent(message.getContent());
		m.setAgentUserMainServerId(agent.getId());
		m.setRegUsrInfo(convertRegisterUser(message.getSender()));
		m.setMainServerId(message.getId());
		
		return m;
	}
	
	public static MainServerReservation convertReservation(com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation reservation) throws DatatypeConfigurationException {
		MainServerReservation r = new MainServerReservation();
		r.setBookingUnitMainServerId(reservation.getBookingUnit().getId());
		r.setFrom(dateToXMLCalendar(reservation.getFromDate()));
		r.setTo(dateToXMLCalendar(reservation.getToDate()));
		r.setSubjectName(reservation.getSubjectName());
		r.setSubjectSurname(reservation.getSubjectSurname());
		r.setMainServerId(reservation.getId());
		r.setTotalPrice(reservation.getTotalPrice());
		ReservationStatus stat = ReservationStatus.fromValue(reservation.getReservationStatus().name());
		r.setReservationStatus(stat);
		
		return r;
	}
	
	private static XMLGregorianCalendar dateToXMLCalendar(Date date) throws DatatypeConfigurationException {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
	    
	    return xmlDate;
	}
}
