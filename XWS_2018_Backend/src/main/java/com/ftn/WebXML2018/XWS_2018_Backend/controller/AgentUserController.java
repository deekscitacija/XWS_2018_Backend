package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.AgentRegistrationDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.UserDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AgentUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AgentUserService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.UserService;

@RestController
@RequestMapping("/agents")
public class AgentUserController {

	@Autowired
	private AgentUserService agentService;
	
	@Autowired
	private UserService userService;
	
	//Ispeglati na frontu pripremu grada i RegisteredUser-a
	@PostMapping("/new")
	public ResponseEntity<?> createAgent(@RequestBody AgentRegistrationDTO agent) {
		ResponseWrapper<AgentUser> response = null;
		AgentUser retFromService = null;
		AgentUser agentUser = null;
		String message = "Agent registration failed. Please, try again later.";
		ResponseWrapper<AgentUser> resp = null;
		ResponseEntity<ResponseWrapper<AgentUser>> ret = null;
		
		User regUser = userService.register(agent.getUsername(), agent.getPassword(), agent.getName(), agent.getSurname(),
										agent.getCity(), agent.getState(), agent.getHomeAddress(), agent.getEmail(), 
										agent.getPhoneNumber(), agent.getPostCode());
		if(regUser != null) {
			try {
				agentUser = new AgentUser();
				agentUser.setEmail(agent.getEmail());
				agentUser.setId(regUser.getId());
				agentUser.setPmb(agent.getPmb());
				retFromService = agentService.save(agentUser);
				resp = new ResponseWrapper<AgentUser>(retFromService, "Agent registered successfully!", true);
				
				return ResponseEntity.ok(resp);
			} catch(Exception e) {
				resp = new ResponseWrapper<AgentUser>(retFromService, message, false);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
			}
		} else {
			resp = new ResponseWrapper<AgentUser>(retFromService, message, false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		}
	}
}
