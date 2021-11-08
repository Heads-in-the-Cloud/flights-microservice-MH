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
import com.smoothstack.utopia_spring.entity.AirplaneType;
import com.smoothstack.utopia_spring.exception.*;
import com.smoothstack.utopia_spring.service.AirplaneTypeService;

@RestController
@RequestMapping(path = "/airplaneType")
public class AirplaneTypeController {

	@Autowired
	private AirplaneTypeService service;
	
	public AirplaneTypeController(AirplaneTypeService service) {
		
		this.service = service;
	}
	
	//Create
	@PostMapping("/add")
	public ResponseEntity<AirplaneType> addAirplaneType(@RequestBody AirplaneType airplaneType) {
		service.save(airplaneType);
		return ResponseEntity.ok(airplaneType);
	}
	
	//Read
	@GetMapping("/all")
	public ResponseEntity<List<AirplaneType>> readAllAirplaneTypes() {
		
		List<AirplaneType> airplaneTypes = service.readAll();
		if(airplaneTypes.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(airplaneTypes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AirplaneType> readAirplaneTypeById(@PathVariable int id) {
		
		AirplaneType airplaneType = service.readById(id).orElseThrow(IdNotFoundException::new);
		return ResponseEntity.ok(airplaneType);
	}
	
	//Update
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateAirplaneType(@PathVariable int id, @RequestBody AirplaneType airplaneType) {
		
		//Check if path id = id
		if(id != airplaneType.getId()) {
			throw new IdMismatchException();
		}
		//Check if the record to update exists
		AirplaneType temp = service.readById(id).orElseThrow(IdNotFoundException::new);
		service.save(airplaneType);
		return ResponseEntity.ok("AirplaneType updated successfully");
	}
	
	//Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteAirplaneType(@PathVariable int id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
