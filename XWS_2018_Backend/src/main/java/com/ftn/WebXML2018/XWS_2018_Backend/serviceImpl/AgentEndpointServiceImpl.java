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
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.ftn.WebXML2018.XWS_2018_Backend.repository.BookingUnitRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
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
	
	@Override
	@WebMethod
	public String agentLogin() {
		// TODO Celokupna sinhronizacija!!!
		return "Success Timeeeeh!";
	}

	@Override
	@WebMethod
	public ResponseWrapper<Long> addBookingUnit(BookingUnit_DTO buDto) {
		ResponseWrapper<Long> retObj = new ResponseWrapper<Long>();
		
		City city = cityService.getOne(buDto.getCityMainServerId());
		if(city == null) {
			retObj.setSuccess(false);
			retObj.setMessage("Invalid city input. Please try again.");
			return retObj;
		}
		
		AgentUser agentUsr = agentUserService.getById(buDto.getAgentMainServerId());
		if(agentUsr == null) {
			retObj.setSuccess(false);
			retObj.setMessage("Can't find agent data on main server. Please try again.");
			return retObj;
		}
		
		AccomodationType accType = accTypeService.getById(buDto.getAccomodationTypeMainServerId());
		if(accType == null) {
			retObj.setSuccess(false);
			retObj.setMessage("Can't find the given accomodation type data on main server. Please try again.");
			return retObj;
		}
		
		AccomodationCategory accCat = accCatService.getById(buDto.getAccomodationCategoryMainServerId());
		if(accCat == null) {
			retObj.setSuccess(false);
			retObj.setMessage("Can't find the given accomodation category data on main server. Please try again.");
			return retObj;
		}
		
		Set<BonusFeatures> bonusFeatures = new HashSet<BonusFeatures>();
		for(Iterator<Long> i = buDto.getBonusFeaturesMainServerIds().iterator(); i.hasNext();) {
			Long bfId = i.next();
			BonusFeatures bFeature = bFeaturesService.getById(bfId);
			if(bFeature == null) {
				retObj.setSuccess(false);
				retObj.setMessage("Can't find the given bonus feature data on main server. Please try again.");
				return retObj;
			}
			bonusFeatures.add(bFeature);
		}
		
		BookingUnit unit = new BookingUnit(buDto.getAddress(), buDto.getName(), buDto.getDescription(), 
				buDto.getPeopleNo(), city, agentUsr, 
				bonusFeatures, accType, 
				accCat, null);
		
		BookingUnit unitData = bookingUnitService.insertBookingUnit(unit);
		
		for (Map.Entry<String, String> item : buDto.getBase64ImagesList().entrySet()) {
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
		return retObj;
	}

	@Override
	@WebMethod
	public ResponseWrapper<String> manageMonthlyPrices(MonthlyPrices_DTO mpDto) {
		ResponseWrapper<String> retObj = new ResponseWrapper<String>();
		
		BookingUnit unit = bookingUnitService.findById(mpDto.getBookingUnitMainServerId());
		if(unit == null) {
			retObj.setSuccess(false);
			retObj.setMessage("Wrong booking unit data. Please try again.");
			return retObj;
		}
		
		boolean isInsert = true;
		double[] monthlyPrices = mpDto.getMonthlyPrices();
		MonthlyPrices mPrice = monthlyPricesService.findOneByBookingUnitAndMonthAndYear(unit, 1, mpDto.getYear());
		if(mPrice == null)
		{
			//add new
			for (int i = 0; i < monthlyPrices.length; i++) {
				MonthlyPrices mp = new MonthlyPrices(monthlyPrices[i], i + 1, mpDto.getYear(), unit);
				monthlyPricesService.insertMonthlyPrices(mp);
			}
		}
		else {
			//update the old ones
			for (int i = 0; i < monthlyPrices.length; i++) {
				MonthlyPrices oldPrice = monthlyPricesService.findOneByBookingUnitAndMonthAndYear(unit, i + 1, mpDto.getYear());
				oldPrice.setAmount(monthlyPrices[i]);
			}
			
			isInsert = false;
		}
		
		retObj.setSuccess(true);
		if(isInsert)
			retObj.setResponseBody("INSERT");
		else
			retObj.setResponseBody("UPDATE");
		return retObj;
	}

	@Override
	@WebMethod
	public ResponseWrapper<?> addLocalReservation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public ResponseWrapper<?> sendMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public ResponseWrapper<?> confirmReservation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public ResponseWrapper<?> cancelReservation() {
		// TODO Auto-generated method stub
		return null;
	}

}
