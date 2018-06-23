package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ftn.WebXML2018.XWS_2018_Backend.agentDto.BookingUnit_DTO;
import com.ftn.WebXML2018.XWS_2018_Backend.agentDto.MonthlyPrices_DTO;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.AgentUserDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationCategory;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationType;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AgentUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BonusFeatures;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnitPicture;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.MonthlyPrices;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.BookingUnitRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AccomodationCategoryService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AccomodationTypeService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AgentEndpointService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AgentUserService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BonusFeaturesService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BookingUnitPictureService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BookingUnitService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.CityService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.MessageService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.MonthlyPricesService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.ReservationService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.UserService;
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
import com.ftn_booking.agentendpoint.HMapStringStringElement;
import com.ftn_booking.agentendpoint.ManageMonthlyPricesRequest;
import com.ftn_booking.agentendpoint.ManageMonthlyPricesResponse;
import com.ftn_booking.agentendpoint.ResponseWrapper;
import com.ftn_booking.agentendpoint.SendMessageRequest;
import com.ftn_booking.agentendpoint.SendMessageResponse;
import com.ftn_booking.agentendpoint.SinchronizationObject;

@Endpoint
public class AgentEndpointServiceImpl {

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
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private AccomodationTypeService accTypeService;
	
	@Autowired
	private AccomodationCategoryService accCatService;
	
	@Autowired
	private AgentUserService agentUserService;
	
	@Autowired
	private BonusFeaturesService bFeaturesService;
	
	@Autowired
	private BookingUnitPictureService bUnitPictureService;
	
	@PayloadRoot(namespace = "http://ftn-booking.com/agentEndpoint", localPart = "agentLoginRequest")
	@ResponsePayload
	public AgentLoginResponse agentLogin(@RequestPayload AgentLoginRequest alRequest) {
		AgentLoginResponse response = new AgentLoginResponse();
		ResponseWrapper retObj = new ResponseWrapper();
		
		User agentUsr = userService.getByUsername(alRequest.getUserName());
		if(agentUsr == null) {
			retObj.setMessage("Agent with the given email does not exist.");
			retObj.setSuccess(false);
			response.setResponseWrapper(retObj);
			return response;
		}
		else if(!agentUsr.getPassword().equals(alRequest.getPassword())){
			retObj.setMessage("Incorrect password.");
			retObj.setSuccess(false);
			response.setResponseWrapper(retObj);
			return response;
		}
		else {
			com.ftn_booking.agentendpoint.SinchronizationObject synchObj = new SinchronizationObject();	
			
		}			
		
		return response;
	}
	
	@PayloadRoot(namespace = "http://ftn-booking.com/agentEndpoint", localPart = "addBookingUnitRequest")
	@ResponsePayload
	public AddBookingUnitResponse addBookingUnit(@RequestPayload  AddBookingUnitRequest abuRequest) {
		AddBookingUnitResponse response = new AddBookingUnitResponse();
		ResponseWrapper retObj = new ResponseWrapper();
		com.ftn_booking.agentendpoint.BookingUnit requestUnit = abuRequest.getBookingUnit();
		
		City city = cityService.getOne(requestUnit.getCityMainServerId());
		if(city == null) {
			retObj.setSuccess(false);
			retObj.setMessage("Invalid city input. Please try again.");
			response.setResponseWrapper(retObj);
			return response;
		}
		
		AgentUser agentUsr = agentUserService.getById(requestUnit.getAgentMainServerId());
		if(agentUsr == null) {
			retObj.setSuccess(false);
			retObj.setMessage("Can't find agent data on main server. Please try again.");
			response.setResponseWrapper(retObj);
			return response;
		}
		
		AccomodationType accType = accTypeService.getById(requestUnit.getAccTypeMainServerId());
		if(accType == null) {
			retObj.setSuccess(false);
			retObj.setMessage("Can't find the given accomodation type data on main server. Please try again.");
			response.setResponseWrapper(retObj);
			return response;
		}
		
		AccomodationCategory accCat = accCatService.getById(requestUnit.getAccCategoryMainServerId());
		if(accCat == null) {
			retObj.setSuccess(false);
			retObj.setMessage("Can't find the given accomodation category data on main server. Please try again.");
			response.setResponseWrapper(retObj);
			return response;
		}
		
		Set<BonusFeatures> bonusFeatures = new HashSet<BonusFeatures>();
		for(Iterator<Long> i = requestUnit.getBonusFeaturesMainServerIds().iterator(); i.hasNext();) {
			Long bfId = i.next();
			BonusFeatures bFeature = bFeaturesService.getById(bfId);
			if(bFeature == null) {
				retObj.setSuccess(false);
				retObj.setMessage("Can't find the given bonus feature data on main server. Please try again.");
				response.setResponseWrapper(retObj);
				return response;
			}
			bonusFeatures.add(bFeature);
		}
		
		BookingUnit unit = new BookingUnit(requestUnit.getAddress(), requestUnit.getName(), requestUnit.getDescription(), 
				requestUnit.getPeopleNo(), city, agentUsr, 
				bonusFeatures, accType, 
				accCat, null);
		
		BookingUnit unitData = bookingUnitService.insertBookingUnit(unit);
		
		for (Iterator<HMapStringStringElement> i = requestUnit.getBase64ImagesList().iterator(); i.hasNext();) {
			HMapStringStringElement item = i.next();
		    
			String base64Img = item.getValue();
		    String imgName = item.getKey();
		   
		    byte[] data = Base64.getDecoder().decode(base64Img);
		    
		    String[] tokens = imgName.split("\\.(?=[^\\.]+$)");
		    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));
		    
		    imgName = tokens[0] + timeStamp + tokens[1];
		    
			Path destinationFile = Paths.get("/images", imgName);
			try {
				Files.write(destinationFile, data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			BookingUnitPicture myPicture = new BookingUnitPicture(imgName, unitData);
			bUnitPictureService.insertBookingUnitPicture(myPicture);
		}
				
		retObj.setSuccess(true);
		retObj.setResponseBody(unitData.getId());
		response.setResponseWrapper(retObj);
		return response;
	}

	@PayloadRoot(namespace = "http://ftn-booking.com/agentEndpoint", localPart = "manageMonthlyPricesRequest")
	@ResponsePayload
	public ManageMonthlyPricesResponse manageMonthlyPrices(@RequestPayload ManageMonthlyPricesRequest mmpRequest) {
		ManageMonthlyPricesResponse response = new ManageMonthlyPricesResponse();
		ResponseWrapper retObj = new ResponseWrapper();
		com.ftn_booking.agentendpoint.MonthlyPrices requestMPrice = mmpRequest.getMonthlyPrice();
		
		BookingUnit unit = bookingUnitService.findById(requestMPrice.getMainServerId());
		if(unit == null) {
			retObj.setSuccess(false);
			retObj.setMessage("Wrong booking unit data. Please try again.");
			response.setResponseWrapper(retObj);
			return response;
		}
		
		boolean isInsert = true;
		List<Double> monthlyPrices = requestMPrice.getMonthlyPrices();
		MonthlyPrices mPrice = monthlyPricesService.findOneByBookingUnitAndMonthAndYear(unit, 1, requestMPrice.getYear());
		if(mPrice == null)
		{
			//add new
			for (int i = 0; i < monthlyPrices.size(); i++) {
				MonthlyPrices mp = new MonthlyPrices(monthlyPrices.get(i), i + 1, requestMPrice.getYear(), unit);
				monthlyPricesService.insertMonthlyPrices(mp);
			}
		}
		else {
			//update the old ones
			for (int i = 0; i < monthlyPrices.size(); i++) {
				MonthlyPrices oldPrice = monthlyPricesService.findOneByBookingUnitAndMonthAndYear(unit, i + 1, requestMPrice.getYear());
				oldPrice.setAmount(monthlyPrices.get(i));
			}			
			isInsert = false;
		}
		
		retObj.setSuccess(true);
		if(isInsert)
			retObj.setResponseBody("INSERT");
		else
			retObj.setResponseBody("UPDATE");
		
		response.setResponseWrapper(retObj);
		return response;
	}

	@PayloadRoot(namespace = "http://ftn-booking.com/agentEndpoint", localPart = "addLocalReservationRequest")
	@ResponsePayload
	public AddLocalReservationResponse addLocalReservation(@RequestPayload AddLocalReservationRequest alrRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	public SendMessageResponse sendMessage(SendMessageRequest smRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	public ConfirmReservationResponse confirmReservation(ConfirmReservationRequest confrRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	public CancelReservationResponse cancelReservation(CancelReservationRequest cancrRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
