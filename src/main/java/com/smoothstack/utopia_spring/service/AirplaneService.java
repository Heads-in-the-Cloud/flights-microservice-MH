package com.smoothstack.utopia_spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.utopia_spring.dao.AirplaneDAO;
import com.smoothstack.utopia_spring.entity.Airplane;

@Service
public class AirplaneService {

	@Autowired
	private AirplaneDAO airplanedao;
	
	public AirplaneService(AirplaneDAO airplanedao) {
		this.airplanedao = airplanedao;
	}
	
	public List<Airplane> readAll() {
		
		List<Airplane> airplanes = new ArrayList<Airplane>();
		airplanedao.findAll().forEach(airplane -> airplanes.add(airplane));
		return airplanes;
	}
	
	public Optional<Airplane> readById(int id) {
		
		return airplanedao.findById(id);
	}
	
	public void save(Airplane airplane) {
		
		airplanedao.save(airplane);
	}
	
	public void delete(int id) {
		
		airplanedao.findById(id).ifPresent(airplanedao::delete);
	}
}
