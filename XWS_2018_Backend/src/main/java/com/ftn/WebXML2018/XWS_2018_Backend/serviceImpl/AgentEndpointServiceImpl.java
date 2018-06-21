package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.repository.BookingUnitRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AgentEndpointService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BookingUnitService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.MessageService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.MonthlyPricesService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.ReservationService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.UserService;

// localhost:8081/ftn-booking.com/soap-api/agentEndpointService
@Service
@WebService(targetNamespace="http://agentservice", serviceName="AgentEndpointService", endpointInterface="com.ftn.WebXML2018.XWS_2018_Backend.service.AgentEndpointService")
public class AgentEndpointServiceImpl implements AgentEndpointService{

	@Autowired
	private BookingUnitService bookingUnitService;
	
	@Autowired
	private MonthlyPricesService monthlyPricesService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public ResponseWrapper<?> agentLogin() {
		// TODO Auto-generated method stub
		return new ResponseWrapper<String>("Success Timeeeeh!", "Success!", true);
	}

	@Override
	public ResponseWrapper<?> addBookingUnit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseWrapper<?> manageMonthlyPrices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseWrapper<?> addLocalReservation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseWrapper<?> sendMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseWrapper<?> confirmReservation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseWrapper<?> cancelReservation() {
		// TODO Auto-generated method stub
		return null;
	}

}
