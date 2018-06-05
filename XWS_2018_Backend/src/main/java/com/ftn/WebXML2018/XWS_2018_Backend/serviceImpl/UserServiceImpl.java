package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.RegisteredUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.UserRoles;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.CityRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.CountryRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.RegisteredUserRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.UserRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.UserRolesRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RegisteredUserRepository registeredUserRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private UserRolesRepository userRolesRepository;
	
	@Override
	public User saveUser(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public User getUser(Long id) {
		
		return userRepository.findOne(id);
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
		
		//Kod mene je registrovani korisnik imao id = 2
		UserRoles role = userRolesRepository.getOne(new Long(2));
		
		if(role == null) {
			return null;
		}
		
		User newUser = new User(null, username, password, ime, prezime, role, city, null, null, adresa); 
		
		try {
			newUser = userRepository.save(newUser);
			RegisteredUser regUser = new RegisteredUser(newUser.getId(), email, telefon, true);
			regUser = registeredUserRepository.save(regUser);
		}catch(Exception e) {
			return null;
		}
		
		return newUser;
	}

}
