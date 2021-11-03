package com.smoothstack.utopia_spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.utopia_spring.dao.AirportDAO;
import com.smoothstack.utopia_spring.entity.Airport;

@Service
public class AirportService {

	@Autowired
	private AirportDAO airportdao;
	
	public AirportService(AirportDAO airportdao) {
		this.airportdao = airportdao;
	}
	
	public List<Airport> readAll() {
		
		List<Airport> airports = new ArrayList<Airport>();
		airportdao.findAll().forEach(airport -> airports.add(airport));
		return airports;
	}
	
	public Optional<Airport> readById(String id) {
		
		return airportdao.findById(id);
	}
	
	public void save(Airport airport) {
		
		airportdao.save(airport);
	}
	
	public void delete(String id) {
		
		airportdao.findById(id).ifPresent(airportdao::delete);
	}
}
