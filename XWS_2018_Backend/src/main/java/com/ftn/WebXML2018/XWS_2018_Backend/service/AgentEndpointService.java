package com.ftn.WebXML2018.XWS_2018_Backend.service;

import javax.jws.WebService;

import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ftn_booking.agentendpoint.AddBookingUnitRequest;
import com.ftn_booking.agentendpoint.AddBookingUnitResponse;
import com.ftn_booking.agentendpoint.AddLocalReservationRequest;
import com.ftn_booking.agentendpoint.AddLocalReservationResponse;
import com.ftn_booking.agentendpoint.AgentLoginRequest;
import com.ftn_booking.agentendpoint.AgentLoginResponse;
import com.ftn_booking.agentendpoint.CancelReservationRequest;
import com.ftn_booking.agentendpoint.CancelReservationResponse;
import com.ftn_booking.agentendpoint.ConfirmReservationRequest;
import com.ftn_booking.agentendpoint.ConfirmReservationResponse;
import com.ftn_booking.agentendpoint.ManageMonthlyPricesRequest;
import com.ftn_booking.agentendpoint.ManageMonthlyPricesResponse;
import com.ftn_booking.agentendpoint.SendMessageRequest;
import com.ftn_booking.agentendpoint.SendMessageResponse;

@WebService
public interface AgentEndpointService {

	@PayloadRoot(namespace = "http://ftn-booking.com/agentEndpoint", localPart = "agentLoginRequest")
	@ResponsePayload
	public AgentLoginResponse agentLogin(@RequestPayload AgentLoginRequest alRequest);
	
	@PayloadRoot(namespace = "http://ftn-booking.com/agentEndpoint", localPart = "addBookingUnitRequest")
	@ResponsePayload
	public AddBookingUnitResponse addBookingUnit(@RequestPayload AddBookingUnitRequest abuRequest);
	
	@PayloadRoot(namespace = "http://ftn-booking.com/agentEndpoint", localPart = "manageMonthlyPricesRequest")
	@ResponsePayload
	public ManageMonthlyPricesResponse manageMonthlyPrices(@RequestPayload ManageMonthlyPricesRequest mmpRequest);
	
	@PayloadRoot(namespace = "http://ftn-booking.com/agentEndpoint", localPart = "addLocalReservationRequest")
	@ResponsePayload
	public AddLocalReservationResponse addLocalReservation(@RequestPayload AddLocalReservationRequest alrRequest);
	
	@PayloadRoot(namespace = "http://ftn-booking.com/agentEndpoint", localPart = "sendMessageRequest")
	@ResponsePayload
	public SendMessageResponse sendMessage(@RequestPayload SendMessageRequest smRequest);
	
	@PayloadRoot(namespace = "http://ftn-booking.com/agentEndpoint", localPart = "confirmReservationRequest")
	@ResponsePayload
	public ConfirmReservationResponse confirmReservation(@RequestPayload ConfirmReservationRequest confrRequest);
	
	@PayloadRoot(namespace = "http://ftn-booking.com/agentEndpoint", localPart = "cancelReservationRequest")
	@ResponsePayload
	public CancelReservationResponse cancelReservation(@RequestPayload CancelReservationRequest cancrRequest);
}
