package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.AgentApproveDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.AgentRegistrationDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.AgentUserDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.UserDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.UserMiniDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AgentUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.RegisteredUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.enums.UserRolesType;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AgentUserService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.RegisteredUserService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.UserService;

@RestController
@RequestMapping("/agents")
public class AgentUserController {

	@Autowired
	private AgentUserService agentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RegisteredUserService regUserService;
	
	@PostMapping("/new")
	public ResponseEntity<?> createAgent(@RequestBody AgentRegistrationDTO agent) {
		AgentUser retFromService = null;
		AgentUser agentUser = null;
		String message = "Agent registration failed. Please, try again later.";
		ResponseWrapper<AgentUser> resp = null;
		
		User regUser = userService.register(UserRolesType.AGENT, agent.getUsername(), agent.getPassword(), agent.getName(), agent.getSurname(),
										agent.getCity(), agent.getState(), agent.getHomeAddress(), agent.getEmail(), 
										agent.getPhoneNumber(), agent.getPostCode());
		if(regUser != null) {
			try {
				agentUser = new AgentUser();
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
	
	@PostMapping("/approve")
	public ResponseEntity<?> approveAgent(@RequestBody AgentApproveDTO agent) {
		try {
			User u = userService.getUser(agent.getId());
			RegisteredUser regu = regUserService.getRegUser(agent.getId());
			
			if(u != null && regu != null) {
				AgentUser a = new AgentUser(agent.getId(), agent.getPmb());
				ResponseWrapper<AgentUser> resp = null;
				regu.setActive(true);
				
				regUserService.saveRegUser(regu);
				agentService.save(a);
				resp = new ResponseWrapper<AgentUser>(a, "Success!", true);
				
				return ResponseEntity.ok(resp);
			} else {
				ResponseWrapper<AgentUser> resp = new ResponseWrapper<AgentUser>(null, "Agent not found.", false);
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(resp);
			}
		} catch(Exception e) {
			e.printStackTrace();
			ResponseWrapper<AgentUser> resp = new ResponseWrapper<AgentUser>(null, "Error approving agent.", false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		}
	}
	
	@PostMapping("/decline")
	public ResponseEntity<?> declineAgent(@RequestBody AgentApproveDTO agent) {
		try {
			User u = userService.getUser(agent.getId());
			RegisteredUser regu = regUserService.getRegUser(agent.getId());
			
			if(u != null && regu != null) {
				AgentUser a = new AgentUser(agent.getId(), agent.getPmb());
				ResponseWrapper<AgentUser> resp = null;
				regu.setActive(false);
				
				regUserService.saveRegUser(regu);
				agentService.save(a);
				resp = new ResponseWrapper<AgentUser>(a, "Success!", true);
				
				return ResponseEntity.ok(resp);
			} else {
				ResponseWrapper<AgentUser> resp = new ResponseWrapper<AgentUser>(null, "Agent not found.", false);
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(resp);
			}
		} catch(Exception e) {
			ResponseWrapper<AgentUser> resp = new ResponseWrapper<AgentUser>(null, "Error declining agent.", false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		}
	}
	
	@GetMapping("/getByActive/{active}")
	public ResponseEntity<?> getAgentsByActivated(@PathVariable("active") Boolean active) {
		List<AgentUserDTO> ret;
		ResponseWrapper<?> resp;
		
		try {
			if(active) {
				ret = agentService.getAllActivated();
				resp = new ResponseWrapper<List<AgentUserDTO>>(ret, "Success!", true);	
			} else {
				ret = agentService.getAllDeactivated();
				resp = new ResponseWrapper<List<AgentUserDTO>>(ret, "Success!", true);
			}
			
			return ResponseEntity.ok(resp); 
		} catch(Exception e) {
			resp = new ResponseWrapper<List<AgentUser>>(null, "Fail :(", true);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		}
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAll() {
		List<AgentUserDTO> ret;
		ResponseWrapper<List<AgentUserDTO>> resp;
		
		try {
			ret = agentService.getAll();
			resp = new ResponseWrapper<List<AgentUserDTO>>(ret, "Success!", true);
			
			return ResponseEntity.ok(resp); 
		} catch(Exception e) {
			resp = new ResponseWrapper<List<AgentUserDTO>>(null, "Fail :(", false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		}
	}
	
	@GetMapping("/byPmb/{pmb}")
	public ResponseEntity<?> getByPmb(@PathVariable("pmb") String pmb) {
		AgentUserDTO user;
		ResponseWrapper<AgentUserDTO> resp;
		
		try {
			user = agentService.getByPmb(pmb);
			resp = new ResponseWrapper<AgentUserDTO>(user, "Success!", true);
			
			return ResponseEntity.ok(resp);
		} catch(Exception e) {
			resp = new ResponseWrapper<AgentUserDTO>(null, "Fail :(", false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		}
	}
}
