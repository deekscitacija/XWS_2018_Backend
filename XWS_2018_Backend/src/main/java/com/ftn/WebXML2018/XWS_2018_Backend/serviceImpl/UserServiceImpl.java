package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.UserDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.RegisteredUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.UserRoles;
import com.ftn.WebXML2018.XWS_2018_Backend.enums.UserRolesType;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.AgentUserRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.CityRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.CountryRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.RegisteredUserRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.UserRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.UserRolesRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.security.TokenUtils;
import com.ftn.WebXML2018.XWS_2018_Backend.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RegisteredUserRepository registeredUserRepository;
	
	@Autowired
	private AgentUserRepository agentUserRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private UserRolesRepository userRolesRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/* ------- METODE --------- */
	
	@Override
	public User saveUser(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public User getUser(Long id) {
		
		return userRepository.findOne(id);
	}
	
	@Override
	public User getByUsername(String username) {
		
		return userRepository.getByUsername(username);
	}

	@Override
	public User register(String username, String password, String ime, String prezime, String grad, String drzava,
			String adresa, String email, String telefon, String postbroj) {
		
		if(username.isEmpty() || password.isEmpty() || ime.isEmpty() || prezime.isEmpty() || grad.isEmpty() || drzava.isEmpty()) {
			return null;
		}
		
		Long drzavaIdVal = null;
		
		try {
			drzavaIdVal = Long.parseLong(drzava);
		}catch(NumberFormatException e) {
			return null;
		}
		
		Country country = countryRepository.findOne(drzavaIdVal);
		
		if(country == null) {
			return null;
		}
		
		List<City> tempCities = cityRepository.findByCountryAndNameAndPostcode(country, grad, postbroj);
		City city = null;
		
		if(!tempCities.isEmpty()) {
			city = tempCities.get(0);
		}else {
			city = new City(grad, country, postbroj);
			city = cityRepository.save(city);
		}
		
		UserRoles role = userRolesRepository.getByName(UserRolesType.REG_USER);
		
		if(role == null) {
			return null;
		}
		
		User newUser = new User(null, username, passwordEncoder.encode(password), ime, prezime, role, city, null, null, adresa); 
		
		try {
			newUser = userRepository.save(newUser);
			RegisteredUser regUser = new RegisteredUser(newUser.getId(), email, telefon, false);	//Admin aktivira korisnike!
			regUser = registeredUserRepository.save(regUser);
		}catch(Exception e) {
			return null;
		}
		
		return newUser;
	}

	@Override
	public User getUserFromToken(ServletRequest request, TokenUtils tokenUtils) {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String token = httpRequest.getHeader("token");
		
		if(token == null) {
			System.out.println("TOKEN JE NULL!");
			return null;
		}
		
		String username = tokenUtils.getUsernameFromToken(token);

		return userRepository.getByUsername(username);
	}

	@Override
	public User changeUserInfo(User user, String ime, String prezime, String grad, String drzava, String adresa,
			String email, String telefon, String postbroj) {
		
		if(!ime.equals(user.getName()) && !ime.isEmpty()) {
			user.setName(ime);
		}
		
		if(!prezime.equals(user.getSurname()) && !prezime.isEmpty()) {
			user.setSurname(prezime);
		}
		
		Long drzavaIdVal = null;
		
		try {
			drzavaIdVal = Long.parseLong(drzava);
		}catch(NumberFormatException e) {
			return null;
		}
		
		Country country = countryRepository.findOne(drzavaIdVal);
		
		if(country == null) {
			return null;
		}
		
		List<City> tempCities = cityRepository.findByCountryAndNameAndPostcode(country, grad, postbroj);
		City city = null;
		
		if(!tempCities.isEmpty()) {
			city = tempCities.get(0);
		}else {
			city = new City(grad, country, postbroj);
			city = cityRepository.save(city);
		}
		
		if(!user.getCity().getId().equals(city.getId())) {
			user.setCity(city);
		}
		
		if(!adresa.equals(user.getHomeAddress())) {
			user.setHomeAddress(adresa.isEmpty() ? null : adresa);
		}
		
		if(!email.equals(user.getRegisteredUser().getEmail()) && !email.isEmpty()) {
			user.getRegisteredUser().setEmail(email);
		}
		
		if(!telefon.equals(user.getRegisteredUser().getPhoneNumber())) {
			user.getRegisteredUser().setPhoneNumber(telefon.isEmpty() ? null : telefon);
		}
		
		registeredUserRepository.save(user.getRegisteredUser());
		
		return userRepository.save(user);
	}

	@Override
	public User activateUser(Long id) {
		User u = null;
		try {
			u = userRepository.findOne(id);
			
			if(u.getUserRole().getName().equals(UserRolesType.REG_USER)) {
				RegisteredUser ru = u.getRegisteredUser();
				ru.setActive(true);
				registeredUserRepository.save(ru);
				u.setRegisteredUser(ru);
				u = userRepository.save(u);
			} else {
				u = null;
			}
		} catch(HibernateException e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public User blockUser(Long id) {
		User u = null;
		try {
			u = userRepository.findOne(id);
			
			if(u.getUserRole().getName().equals(UserRolesType.REG_USER)) {
				RegisteredUser ru = u.getRegisteredUser();
				ru.setActive(false);
				registeredUserRepository.save(ru);
				u.setRegisteredUser(ru); 
				u = userRepository.save(u);
			} else {
				u = null;
			}
		} catch(HibernateException e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public User deleteUser(Long id) {
		User u = null;
		try {
			u = userRepository.findOne(id);
			RegisteredUser ru = u.getRegisteredUser();
			
			if(u.getUserRole().getName().equals(UserRolesType.REG_USER)) {
				registeredUserRepository.delete(ru);
				userRepository.delete(u);
			} else {
				u = null;
			}
		} catch(HibernateException e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public List<User> getActivatedUsers() {
		List<User> ret = new ArrayList<User>();
		List<RegisteredUser> regUsers = new ArrayList<RegisteredUser>();
		List<Long> idList = new ArrayList<Long>();
		UserRoles userRole = userRolesRepository.getByName(UserRolesType.REG_USER);

		try {
			ret = userRepository.findAllByUserRole(userRole);
			
			for(User u : ret) {
				idList.add(u.getId());
			}
			
			regUsers = registeredUserRepository.findAllByIdInAndActive(idList, true);
			idList.clear();
			
			for(RegisteredUser reg : regUsers) {
				idList.add(reg.getId());
			}
			
			ret = userRepository.findAllByIdIn(idList);
		} catch(Exception e) {
			ret = null;
		}
		
		return ret;
	}

	@Override
	public List<User> getDisabledUsers() {
		List<User> ret = new ArrayList<User>();
		List<RegisteredUser> regUsers = new ArrayList<RegisteredUser>();
		List<Long> idList = new ArrayList<Long>();
		UserRoles userRole = userRolesRepository.getByName(UserRolesType.REG_USER);
		
		try {
			ret = userRepository.findAllByUserRole(userRole);
			
			for(User u : ret) {
				idList.add(u.getId());
			}
			
			regUsers = registeredUserRepository.findAllByIdInAndActive(idList, false);
			idList.clear();
			
			for(RegisteredUser reg : regUsers) {
				idList.add(reg.getId());
			}
			
			ret = userRepository.findAllByIdIn(idList);
		} catch(Exception e) {
			ret = null;
		}
		
		return ret;
	}

	@Override
	public UserDTO convertToDTO(User u) {
		UserDTO dto = new UserDTO();
		
		dto.setCity(u.getCity());
		dto.setHomeAddress(u.getHomeAddress());
		dto.setId(u.getId());
		dto.setName(u.getName());
		dto.setRegisteredUser(u.getRegisteredUser());
		dto.setSurname(u.getSurname());
		dto.setUsername(u.getUsername());
		dto.setUserRole(u.getUserRole());

		return dto;
	}

}
