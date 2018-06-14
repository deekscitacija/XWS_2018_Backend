package com.ftn.WebXML2018.XWS_2018_Backend.service;

import java.util.List;

import javax.servlet.ServletRequest;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.UserDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.security.TokenUtils;

public interface UserService {
	
	public User saveUser(User user);
	
	public User getUser(Long id);
	
	public User getByUsername(String username);
	
	public User register(String username, String password, String ime, String prezime, String grad, String drzava,
			String adresa, String email, String telefon, String postbroj);

	public User getUserFromToken(ServletRequest request, TokenUtils tokenUtils);
	
	public User changeUserInfo(User user, String ime, String prezime, String grad, String drzava, String adresa, 
			String email, String telefon, String postbroj);
	
	public User activateUser(Long id);
	
	public User blockUser(Long id);
	
	public User deleteUser(Long id);
	
	public List<User> getActivatedUsers();
	
	public List<User> getDisabledUsers();
	
	public UserDTO convertToDTO(User u);
}
