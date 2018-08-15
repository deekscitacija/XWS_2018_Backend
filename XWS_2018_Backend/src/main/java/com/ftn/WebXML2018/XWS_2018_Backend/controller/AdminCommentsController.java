package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.CloudCommentsDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.CloudResponseDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.CommentDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.service.ReservationService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.UserService;

@RestController
@RequestMapping("comments/")
public class AdminCommentsController {

	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="getCommentsForApproval", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?> getCommentsForApproval(){
		
		RestTemplate restTemplate = new RestTemplate();
		
	    ResponseEntity<CloudCommentsDTO> response = restTemplate.getForEntity("https://rating-functions.azurewebsites.net/api/GetCommentsForApproval?code=gareDTo8TyRCa7pHELHqj4aXjbR0714kUOoZwTFhAkDyCK6hDqerpg==",  CloudCommentsDTO.class);
		
	    ArrayList<CommentDTO> retVal = new ArrayList<CommentDTO>();
	    
	    for(CloudResponseDTO cloudResponseDTO : response.getBody().getComments()) {
	    	
	    	Reservation reservation = reservationService.findById(cloudResponseDTO.getReservation_id());
	    	if(reservation==null) {
	    		continue;
	    	}
	    	
	    	retVal.add(new CommentDTO(userService.getUser(reservation.getRegisteredUser().getId()),reservation,cloudResponseDTO.getComment(),cloudResponseDTO.getCommentStatus()));
	    }

	    ResponseWrapper<ArrayList<CommentDTO>> ret = new ResponseWrapper<ArrayList<CommentDTO>>(retVal, "Uspesno vraceni komentari!", true);
		return ResponseEntity.ok(ret);
	}

	
}
