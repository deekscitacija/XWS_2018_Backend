package com.ftn.WebXML2018.XWS_2018_Backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.RegisteredUser;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long>{

	public List<RegisteredUser> findAllByActive(boolean active);
	
	public List<RegisteredUser> findAllByIdIn(List<Long> idList);
	
	public List<RegisteredUser> findAllByIdInAndActive(List<Long> idList, boolean active);
}
