package com.smoothstack.utopia_spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.utopia_spring.dao.AirplaneTypeDAO;
import com.smoothstack.utopia_spring.entity.AirplaneType;

@Service
public class AirplaneTypeService {

	@Autowired
	private AirplaneTypeDAO airplaneTypedao;
	
	public AirplaneTypeService(AirplaneTypeDAO airplaneTypedao) {
		this.airplaneTypedao = airplaneTypedao;
	}
	
	public List<AirplaneType> readAll() {
		
		List<AirplaneType> airplaneTypes = new ArrayList<AirplaneType>();
		airplaneTypedao.findAll().forEach(airplaneType -> airplaneTypes.add(airplaneType));
		return airplaneTypes;
	}
	
	public Optional<AirplaneType> readById(int id) {
		
		return airplaneTypedao.findById(id);
	}
	
	public void save(AirplaneType airplaneType) {
		
		airplaneTypedao.save(airplaneType);
	}
	
	public void delete(int id) {
		
		airplaneTypedao.findById(id).ifPresent(airplaneTypedao::delete);
	}
}
