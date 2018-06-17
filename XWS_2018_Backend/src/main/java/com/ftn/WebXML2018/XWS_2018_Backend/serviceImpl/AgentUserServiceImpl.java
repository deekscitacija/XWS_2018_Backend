package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.AgentUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.enums.UserRolesType;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.AgentUserRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.UserRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.UserRolesRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AgentUserService;

@Service
public class AgentUserServiceImpl implements AgentUserService{

	@Autowired
	private AgentUserRepository agentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRolesRepository userRoleRepository;
	
	@Override
	public AgentUser save(AgentUser agent) {
		User user = userRepository.getOne(agent.getId());
		user.setUserRole(userRoleRepository.getByName(UserRolesType.AGENT));
		userRepository.save(user);
		AgentUser ret = agentRepository.save(agent);
		
		return ret;
	}
}