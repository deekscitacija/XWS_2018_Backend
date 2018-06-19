package com.ftn.WebXML2018.XWS_2018_Backend.securityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.UserRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.securityBeans.CustomUserDetailsFactory;

@Service
@ComponentScan("repository")
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User korisnik = userRepository.getByUsername(username);
		
		if(korisnik==null) {
			throw new UsernameNotFoundException("Korisnik ne postoji");
		}else {
			return CustomUserDetailsFactory.createCustomUserDetails(korisnik);
		}
			
		
	}

}
