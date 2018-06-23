package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.MonthlyPrices;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.enums.ReservationStatus;
import com.ftn.WebXML2018.XWS_2018_Backend.helpClasses.Entity2SoapConverter;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.BookingUnitRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AccomodationCategoryService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AccomodationTypeService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AgentEndpointService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AgentUserService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BonusFeaturesService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BookingUnitPictureService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BookingUnitService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.CityService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.CountryService;
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
import com.ftn_booking.agentendpoint.Message;
import com.ftn_booking.agentendpoint.Reservation;
import com.ftn_booking.agentendpoint.ReservationLite;
import com.ftn_booking.agentendpoint.ResponseWrapper;
import com.ftn_booking.agentendpoint.SendMessageRequest;
import com.ftn_booking.agentendpoint.SendMessageResponse;
import com.ftn_booking.agentendpoint.SinchronizationObject;

@Endpoint
public class AgentEndpointServiceImpl {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccomodationCategoryService accomodationCategoryService;
	
	@Autowired
	private AccomodationTypeService accomodationTypeService;
	
	@Autowired
	private BonusFeaturesService bonusFeaturesService;
	
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
	private CountryService countryService;
	
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
		AgentUser agent = null;
		if(agentUsr == null) {
			retObj.setMessage("Agent with the given email does not exist.");
			retObj.setSuccess(false);
			response.setResponseWrapper(retObj);
			return response;
		}
		else if(!passwordEncoder.matches(agentUsr.getPassword(), alRequest.getPassword())) {
			retObj.setMessage("Incorrect password.");
			retObj.setSuccess(false);
			response.setResponseWrapper(retObj);
			return response;
		}
		else {
			try {
				agent = agentUserService.getById(agentUsr.getId());
				SinchronizationObject synchObj = createSyncObject(agentUsr, agent);
				
			} catch(Exception e) {
				retObj.setMessage("Synchronization failed. Please, try again.");
				retObj.setSuccess(false);
				response.setResponseWrapper(retObj);
			}
		}			
		
		return response;
	}
	
	private SinchronizationObject createSyncObject(User u, AgentUser agentUser) {
		SinchronizationObject synchObj = new SinchronizationObject();	
		List<City> cities = cityService.getAll();
		List<Country> countries = countryService.getAll();
		List<AccomodationCategory> categories = accomodationCategoryService.getAll();
		List<AccomodationType> types = accomodationTypeService.getAll();
		List<BonusFeatures> features = bonusFeaturesService.getAll();
		List<com.ftn.WebXML2018.XWS_2018_Backend.entity.Message> sentMessages = messageService.findBySenderOrRecipientNonPageable(u, true);
		List<com.ftn.WebXML2018.XWS_2018_Backend.entity.Message> recievedMessages = messageService.findBySenderOrRecipientNonPageable(u, false);
		List<BookingUnit> bookingUnits = bookingUnitService.findBookingUnitByAgent(agentUser);
		List<com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation> reservations = new ArrayList<>();
		
		try {
			bookingUnits.stream().forEach(bu -> reservations.addAll(reservationService.findReservationsByBookingUnit(bu)));
			
			countries.stream().forEach(country -> synchObj.getCountriesList()
							  							  .add(Entity2SoapConverter.convertCountry(country)));
			cities.stream().forEach(city -> synchObj.getCitiesList()
											.add(Entity2SoapConverter.convertCity(city)));
			categories.stream().forEach(category -> synchObj.getAccCatsList()
															.add(Entity2SoapConverter.convertCategory(category)));
			types.stream().forEach(type -> synchObj.getAccTypesList()
													.add(Entity2SoapConverter.convertType(type)));
			features.stream().forEach(feature -> synchObj.getBonusFeaturesList()
														 .add(Entity2SoapConverter.convertBonus(feature)));
			sentMessages.stream().forEach(msg -> synchObj.getRegUserMessagesList()
														.add(Entity2SoapConverter.convertMessage(msg, agentUser)));
			recievedMessages.stream().forEach(msg -> synchObj.getRegUserMessagesList()
														.add(Entity2SoapConverter.convertMessage(msg, agentUser)));
			reservations.stream().forEach(res -> {
				try {
					synchObj.getReservationsList().add(Entity2SoapConverter.convertReservation(res));
				} catch (DatatypeConfigurationException e) {
					e.printStackTrace();
				}
			});
				
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return synchObj;
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
		List<Long> mainServerIds = new ArrayList<Long>();
		
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
				mainServerIds.add(mp.getId());
			}
		}
		else {
			//update the old ones
			for (int i = 0; i < monthlyPrices.size(); i++) {
				MonthlyPrices oldPrice = monthlyPricesService.findOneByBookingUnitAndMonthAndYear(unit, i + 1, requestMPrice.getYear());
				oldPrice.setAmount(monthlyPrices.get(i));
				mainServerIds.add(oldPrice.getId());
			}			
			isInsert = false;
		}
		
		retObj.setSuccess(true);
		retObj.setResponseBody(mainServerIds);
		if(isInsert)
			retObj.setMessage("INSERT");
		else
			retObj.setMessage("UPDATE");
		
		response.setResponseWrapper(retObj);
		return response;
	}

	@PayloadRoot(namespace = "http://ftn-booking.com/agentEndpoint", localPart = "addLocalReservationRequest")
	@ResponsePayload
	public AddLocalReservationResponse addLocalReservation(@RequestPayload AddLocalReservationRequest alrRequest) throws ParseException {
		Reservation reservation = alrRequest.getLocalReservation();
		AddLocalReservationResponse resp = new AddLocalReservationResponse();
		ResponseWrapper wrapper = new ResponseWrapper();
		com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation reserv = new com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation();
		
		BookingUnit booking = bookingUnitService.findById(reservation.getBookingUnitMainServerId());
		reserv.setBookingUnit(booking);
		reserv.setRegisteredUser(null);
		reserv.setReservationStatus(ReservationStatus.WAITING);
		reserv.setSubjectName(reservation.getReserveeFirstName());
		reserv.setSubjectSurname(reservation.getReserveeLastName());
		reserv.setToDate(parseDate(reservation.getDateTo()));
		reserv.setFromDate(parseDate(reservation.getDateFrom()));
		reserv.setTotalPrice(monthlyPricesService.calculateTotalPrice(booking, reserv.getFromDate(), reserv.getToDate()));
		
		try {
			com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation respBody = reservationService.saveReservation(reserv);
			wrapper.setMessage("Adding reservation successfull!");
			wrapper.setSuccess(true);
			wrapper.setResponseBody(respBody.getId());
			resp.setResponseWrapper(wrapper);
		} catch(Exception e) {
			wrapper.setMessage("Adding reservation failed. Please, try again later.");
			wrapper.setSuccess(false);
			wrapper.setResponseBody(null);
			resp.setResponseWrapper(wrapper);
		}
		
		return resp;
	}

	public SendMessageResponse sendMessage(SendMessageRequest smRequest) {
		Message fromRequest = smRequest.getMessage();
		ResponseWrapper wrapper = new ResponseWrapper();
		SendMessageResponse response = new SendMessageResponse();
		com.ftn.WebXML2018.XWS_2018_Backend.entity.Message msg = new com.ftn.WebXML2018.XWS_2018_Backend.entity.Message();
		
		try {
			AgentUser agentSender = agentUserService.getById(fromRequest.getSenderAgentMainServerId());
			
			if(agentSender != null) {
				User recipent = userService.getUser(fromRequest.getReceiverUserMainServerId());
				User sender = userService.getUser(agentSender.getId());
				msg.setContent(fromRequest.getContent());
				msg.setRecipient(recipent);
				msg.setSender(sender);
				com.ftn.WebXML2018.XWS_2018_Backend.entity.Message ret = messageService.saveMessage(msg);
				
				wrapper.setMessage("Success sending message!");
				wrapper.setResponseBody(ret.getId());
				wrapper.setSuccess(true);
				response.setResponseWrapper(wrapper);
			} else {
				wrapper.setMessage("Agent does not exist in database.");
				wrapper.setSuccess(false);
				wrapper.setResponseBody(null);
				response.setResponseWrapper(wrapper);
			}
		} catch(Exception e) {
			wrapper.setMessage("Error sending message. Please, try again later.");
			wrapper.setSuccess(false);
			wrapper.setResponseBody(null);
			response.setResponseWrapper(wrapper);
		}
		
		return response;
	}

	public ConfirmReservationResponse confirmReservation(ConfirmReservationRequest confrRequest) {
		ReservationLite fromRequest = confrRequest.getReservationLite();
		ResponseWrapper wrapper = new ResponseWrapper();
		ConfirmReservationResponse resp = new ConfirmReservationResponse();
		
		try {
			com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation reservation = reservationService.findById(fromRequest.getReservationMainServerId());
			
			if(reservation != null) {
				reservation.setReservationStatus(ReservationStatus.CONFIRMED);
				reservation = reservationService.saveReservation(reservation);
				wrapper.setMessage("Reservation confirmed!.");
				wrapper.setSuccess(true);
				wrapper.setResponseBody(reservation.getId());
				resp.setResponseWrapper(wrapper);
			} else {
				wrapper.setMessage("Reservation not found.");
				wrapper.setSuccess(false);
				wrapper.setResponseBody(null);
				resp.setResponseWrapper(wrapper);
			}
		} catch(Exception e) {
			e.printStackTrace();
			wrapper.setMessage("Error confirming reservation. Please, try again later.");
			wrapper.setSuccess(false);
			wrapper.setResponseBody(null);
			resp.setResponseWrapper(wrapper);
		}
		
		return resp;
	}

	public CancelReservationResponse cancelReservation(CancelReservationRequest cancrRequest) {
		ReservationLite fromRequest = cancrRequest.getReservationLite();
		ResponseWrapper wrapper = new ResponseWrapper();
		CancelReservationResponse resp = new CancelReservationResponse();
		
		try {
			com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation reservation = reservationService.findById(fromRequest.getReservationMainServerId());
			
			if(reservation != null) {
				reservation.setReservationStatus(ReservationStatus.CANCELED);
				reservation = reservationService.saveReservation(reservation);
				wrapper.setMessage("Reservation canceled!.");
				wrapper.setSuccess(true);
				wrapper.setResponseBody(reservation.getId());
				resp.setResponseWrapper(wrapper);
			} else {
				wrapper.setMessage("Reservation not found.");
				wrapper.setSuccess(false);
				wrapper.setResponseBody(null);
				resp.setResponseWrapper(wrapper);
			}
		} catch(Exception e) {
			e.printStackTrace();
			wrapper.setMessage("Error confirming reservation. Please, try again later.");
			wrapper.setSuccess(false);
			wrapper.setResponseBody(null);
			resp.setResponseWrapper(wrapper);
		}
		
		return resp;
	}
	
	private Date parseDate(String dateString) throws ParseException {
	    DateFormat df = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH);
	    Date result =  df.parse(dateString);  
	    System.out.println(result);
	    
	    return result;
	}
}
