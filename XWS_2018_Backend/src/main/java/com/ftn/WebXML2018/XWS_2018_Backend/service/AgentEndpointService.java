package com.ftn.WebXML2018.XWS_2018_Backend.service;

import javax.jws.WebService;

import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;

@WebService
public interface AgentEndpointService {

	public ResponseWrapper<?> agentLogin();
	
	public ResponseWrapper<?> addBookingUnit();
	
	public ResponseWrapper<?> manageMonthlyPrices();
	
	public ResponseWrapper<?> addLocalReservation();
	
	public ResponseWrapper<?> sendMessage();
	
	public ResponseWrapper<?> confirmReservation();
	
	public ResponseWrapper<?> cancelReservation();
}
