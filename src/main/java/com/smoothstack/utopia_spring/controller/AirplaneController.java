package com.smoothstack.utopia_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.utopia_spring.exception.IdNotFoundException;
import com.smoothstack.utopia_spring.entity.Airplane;
import com.smoothstack.utopia_spring.exception.*;
import com.smoothstack.utopia_spring.service.AirplaneService;

@RestController
@RequestMapping(path = "/airplane")
public class AirplaneController {

	@Autowired
	private AirplaneService service;
	
	public AirplaneController(AirplaneService service) {
		
		this.service = service;
	}
	
	//Create
	@PostMapping("/add")
	public ResponseEntity<Airplane> addAirplane(@RequestBody Airplane airplane) {
		
		service.save(airplane);
		return ResponseEntity.ok(airplane);
	}
	
	//Read
	@GetMapping("/all")
	public ResponseEntity<List<Airplane>> readAllAirplanes() {
		
		List<Airplane> airplanes = service.readAll();
		if(airplanes.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(airplanes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Airplane> readAirplaneById(@PathVariable int id) {
		
		Airplane airplane = service.readById(id).orElseThrow(IdNotFoundException::new);
		return ResponseEntity.ok(airplane);
	}
	
	//Update
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateAirplane(@PathVariable int id, @RequestBody Airplane airplane) {
		
		//Check if path id = id
		if(id != airplane.getId()) {
			throw new IdMismatchException();
		}
		//Check if the record to update exists
		Airplane temp = service.readById(id).orElseThrow(IdNotFoundException::new);
		service.save(airplane);
		return ResponseEntity.ok("Airplane updated successfully");
	}
	
	//Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteAirplane(@PathVariable int id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
