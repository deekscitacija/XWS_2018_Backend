package com.ftn.WebXML2018.XWS_2018_Backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.AgentUser;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;

public interface BookingUnitRepository extends JpaRepository<BookingUnit,Long>{

	public List<BookingUnit> findAllByAgent(AgentUser agent);
}
