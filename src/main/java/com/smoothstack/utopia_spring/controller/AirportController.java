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
import com.smoothstack.utopia_spring.entity.Airport;
import com.smoothstack.utopia_spring.exception.*;
import com.smoothstack.utopia_spring.service.AirportService;

@RestController
@RequestMapping(path = "/airport")
public class AirportController {

	@Autowired
	private AirportService service;
	
	public AirportController(AirportService service) {
		
		this.service = service;
	}
	
	//Create
	@PostMapping("/add")
	public ResponseEntity<Airport> addAirport(@RequestBody Airport airport) {
		service.save(airport);
		return ResponseEntity.ok(airport);
	}
	
	//Read
	@GetMapping("/all")
	public ResponseEntity<List<Airport>> readAllAirports() {
		
		List<Airport> airports = service.readAll();
		if(airports.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(airports);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Airport> readAirportById(@PathVariable String id) {
		
		Airport airport = service.readById(id).orElseThrow(IdNotFoundException::new);
		return ResponseEntity.ok(airport);
	}
	
	//Update
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateAirport(@PathVariable String id, @RequestBody Airport airport) {
		
		//Check if path id = id
		if(id != airport.getAirportCode()) {
			throw new IdMismatchException();
		}
		//Check if the record to update exists
		Airport temp = service.readById(id).orElseThrow(IdNotFoundException::new);
		service.save(airport);
		return ResponseEntity.ok("Airport updated successfully");
	}
	
	//Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteAirport(@PathVariable String id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
