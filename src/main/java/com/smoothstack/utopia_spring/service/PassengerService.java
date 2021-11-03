package com.smoothstack.utopia_spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.utopia_spring.dao.PassengerDAO;
import com.smoothstack.utopia_spring.entity.Passenger;

@Service
public class PassengerService {

	@Autowired
	private PassengerDAO passengerdao;
	
	public PassengerService(PassengerDAO passengerdao) {
		this.passengerdao = passengerdao;
	}
	
	public List<Passenger> readAll() {
		
		List<Passenger> passengers = new ArrayList<Passenger>();
		passengerdao.findAll().forEach(passenger -> passengers.add(passenger));
		return passengers;
	}
	
	public Optional<Passenger> readById(int id) {
		
		return passengerdao.findById(id);
	}
	
	public void save(Passenger passenger) {
		
		passengerdao.save(passenger);
	}
	
	public void delete(int id) {
		
		passengerdao.findById(id).ifPresent(passengerdao::delete);
	}
}
