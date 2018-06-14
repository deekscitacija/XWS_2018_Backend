package com.ftn.WebXML2018.XWS_2018_Backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User getByUsername(String username);
	
	public List<User> findAllByIdIn(List<Long> idList);
}
