package com.ftn.WebXML2018.XWS_2018_Backend.securityBeans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.UserRoles;

public class CustomUserDetailsFactory {

	private CustomUserDetailsFactory() {}
	
	public static CustomUserDetails createCustomUserDetails(User korisnik) {
		return new CustomUserDetails(
				korisnik.getUsername(),
				korisnik.getId(),
				mapToGrantedAuthorities(korisnik.getUserRole())
				);
	}
	
	private static List<GrantedAuthority> mapToGrantedAuthorities(UserRoles role) {
        
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
		
		return authorities;
    }
	
}
