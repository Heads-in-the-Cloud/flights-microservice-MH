package com.smoothstack.utopia_spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.utopia_spring.dao.FlightDAO;
import com.smoothstack.utopia_spring.entity.Flight;

@Service
public class FlightService {

	@Autowired
	private FlightDAO flightdao;
	
	public FlightService(FlightDAO flightdao) {
		this.flightdao = flightdao;
	}
	
	public List<Flight> readAll() {
		
		List<Flight> flights = new ArrayList<Flight>();
		flightdao.findAll().forEach(flight -> flights.add(flight));
		return flights;
	}
	
	public Optional<Flight> readById(int id) {
		
		return flightdao.findById(id);
	}
	
	public void save(Flight flight) {
		
		flightdao.save(flight);
	}
	
	public void delete(int id) {
		
		flightdao.findById(id).ifPresent(flightdao::delete);
	}
}
