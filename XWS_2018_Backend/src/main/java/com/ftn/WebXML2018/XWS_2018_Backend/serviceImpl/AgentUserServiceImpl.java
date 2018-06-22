package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.UserMiniDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AgentUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.RegisteredUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.UserRoles;
import com.ftn.WebXML2018.XWS_2018_Backend.enums.UserRolesType;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.AgentUserRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.RegisteredUserRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.UserRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.UserRolesRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.AgentRegistrationDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.AgentUserDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.UserDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AgentUserService;

@Service
public class AgentUserServiceImpl implements AgentUserService{

	@Autowired
	private AgentUserRepository agentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRolesRepository userRoleRepository;
	
	@Autowired
	private RegisteredUserRepository regUserRepository;
	
	@Override
	public AgentUser save(AgentUser agent) {
		User user = userRepository.getOne(agent.getId());
		user.setUserRole(userRoleRepository.getByName(UserRolesType.AGENT));
		userRepository.save(user);
		AgentUser ret = agentRepository.save(agent);
		
		return ret;
	}

	@Override
	public List<AgentUserDTO> getAllActivated() {
		List<AgentUserDTO> ret = new ArrayList<AgentUserDTO>();
		List<AgentUser> agents = null;
		List<RegisteredUser> regusers = null;
		List<User> users = null;
		List<Long> ids = new ArrayList<Long>();
		UserRoles role = userRoleRepository.getByName(UserRolesType.AGENT);
			
		regusers = regUserRepository.findAllByActive(true);
		regusers.stream().forEach(u -> ids.add(u.getId()));
		users = userRepository.findAllByUserRoleAndIdIn(role, ids);
		
		ids.clear();
		users.stream().forEach(u -> ids.add(u.getId()));
		
		agents = agentRepository.getAllByIdIn(ids);
		
		for(int i=0; i<agents.size(); i++) {
			Long id = agents.get(i).getId();
			User u = users.stream().filter(user -> user.getId().equals(id)).findFirst().get();
			RegisteredUser regu = regusers.stream().filter(r -> r.getId().equals(id)).findFirst().get();
			ret.add(convertToDTO(agents.get(i), u, regu));
		}
		
		return ret;
	}

	@Override
	public List<AgentUserDTO> getAll() {
		List<AgentUserDTO> ret = new ArrayList<AgentUserDTO>();
		List<Long> ids = new ArrayList<Long>();
		List<AgentUser> agents = agentRepository.findAll(); 
		
		agents.stream().forEach(a -> ids.add(a.getId()));
		
		List<User> users = userRepository.findAllByIdIn(ids);
		List<RegisteredUser> regUsers = regUserRepository.findAllByIdIn(ids);
		
		for(int i=0; i<agents.size(); i++) {
			Long id = agents.get(i).getId();
			User u = users.stream().filter(user -> user.getId().equals(id)).findFirst().get();
			RegisteredUser regu = regUsers.stream().filter(r -> r.getId().equals(id)).findFirst().get();
			ret.add(convertToDTO(agents.get(i), u, regu));
		}
		
		return ret;
	}

	@Override
	public AgentUserDTO getByPmb(String pmb) {
		AgentUser ret = null;
		ret = agentRepository.getByPmb(pmb);
		
		User u = userRepository.findOne(ret.getId());
		RegisteredUser ru = regUserRepository.findOne(ret.getId());
		
		return convertToDTO(ret, u, ru);
	}

	@Override
	public List<AgentUserDTO> getAllDeactivated() {
		List<AgentUserDTO> ret = new ArrayList<AgentUserDTO>();
		List<UserDTO> userDtos = new ArrayList<UserDTO>();
		List<RegisteredUser> regusers = null;
		List<User> users = null;
		List<Long> ids = new ArrayList<Long>();
		UserRoles role = userRoleRepository.getByName(UserRolesType.AGENT);
			
		regusers = regUserRepository.findAllByActive(false);
		regusers.stream().forEach(u -> ids.add(u.getId()));
		users = userRepository.findAllByUserRoleAndIdIn(role, ids);
		users.stream().forEach(u -> userDtos.add(convertToDTO(u)));
		userDtos.stream().forEach(udto -> ret.add(convertToDTO(udto, null)));
		return ret;
	}
	
	@Override
	public UserDTO convertToDTO(User u) {
		UserDTO dto = new UserDTO();
		
		dto.setId(u.getId());
		dto.setName(u.getName());
		dto.setSurname(u.getSurname());
		dto.setUsername(u.getUsername());
		dto.setCity(u.getCity());
		dto.setRegisteredUser(u.getRegisteredUser());
		dto.setUserRole(u.getUserRole());
		dto.setEmail(u.getEmail());
		dto.setHomeAddress(u.getHomeAddress());
		
		return dto;
	}

	@Override
	public AgentUserDTO convertToDTO(AgentUser agent, User u, RegisteredUser regu) {
		AgentUserDTO ret = new AgentUserDTO();
		
		ret.setId(u.getId());
		ret.setCity(u.getCity().getName());
		ret.setEmail(u.getEmail());
		ret.setHomeAddress(u.getHomeAddress());
		ret.setName(u.getName());
		ret.setPhoneNumber(regu.getPhoneNumber());
		ret.setPmb(agent.getPmb());
		ret.setState(u.getCity().getCountry().getName());
		ret.setSurname(u.getSurname());
		ret.setUsername(u.getUsername());
		
		return ret;
	}

	@Override
	public AgentUserDTO convertToDTO(UserDTO userDTO, String pmb) {
		AgentUserDTO ret = new AgentUserDTO();
		
		ret.setId(userDTO.getId());
		ret.setCity(userDTO.getCity().getName());
		ret.setEmail(userDTO.getEmail());
		ret.setHomeAddress(userDTO.getHomeAddress());
		ret.setName(userDTO.getName());
		ret.setPhoneNumber(userDTO.getRegisteredUser().getPhoneNumber());
		ret.setPmb(pmb);
		ret.setState(userDTO.getCity().getCountry().getName());
		ret.setSurname(userDTO.getSurname());
		ret.setUsername(userDTO.getUsername());
		
		return ret;
	}

	@Override
	public AgentUser getById(Long id) {
		return agentRepository.findOne(id);
	}
}
