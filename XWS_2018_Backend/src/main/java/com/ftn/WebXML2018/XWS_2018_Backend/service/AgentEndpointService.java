package com.ftn.WebXML2018.XWS_2018_Backend.service;

import javax.jws.WebService;

import com.ftn.WebXML2018.XWS_2018_Backend.agentDto.BookingUnit_DTO;
import com.ftn.WebXML2018.XWS_2018_Backend.agentDto.MonthlyPrices_DTO;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;

@WebService
public interface AgentEndpointService {

	public String agentLogin();
	
	public ResponseWrapper<?> addBookingUnit(BookingUnit_DTO buDto);
	
	public ResponseWrapper<?> manageMonthlyPrices(MonthlyPrices_DTO mpDto);
	
	public ResponseWrapper<?> addLocalReservation();
	
	public ResponseWrapper<?> sendMessage();
	
	public ResponseWrapper<?> confirmReservation();
	
	public ResponseWrapper<?> cancelReservation();
}
