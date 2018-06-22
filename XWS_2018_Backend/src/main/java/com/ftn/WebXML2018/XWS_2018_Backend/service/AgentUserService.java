package com.ftn.WebXML2018.XWS_2018_Backend.service;

import java.util.List;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.AgentRegistrationDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.AgentUserDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.UserDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.AgentApproveDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.UserMiniDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AgentUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.RegisteredUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;

public interface AgentUserService {
	
	public AgentUser save(AgentUser agent);
	
	public List<AgentUserDTO> getAllActivated();
	
	public List<AgentUserDTO> getAllDeactivated();
	
	public List<AgentUserDTO> getAll();
	
	public AgentUserDTO getByPmb(String pmb);
	
	public UserDTO convertToDTO(User u);
	
	public AgentUserDTO convertToDTO(AgentUser agent, User u, RegisteredUser regu);
	
	public AgentUserDTO convertToDTO(UserDTO userDTO, String pmb);
	
	public AgentUser getById(Long id);
}
