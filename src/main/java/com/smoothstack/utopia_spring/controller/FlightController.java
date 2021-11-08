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
import com.smoothstack.utopia_spring.entity.Flight;
import com.smoothstack.utopia_spring.exception.*;
import com.smoothstack.utopia_spring.service.FlightService;

@RestController
@RequestMapping(path = "/flight")
public class FlightController {

	@Autowired
	private FlightService service;
	
	public FlightController(FlightService service) {
		
		this.service = service;
	}
	
	//Create
	@PostMapping("/add")
	public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
		service.save(flight);
		return ResponseEntity.ok(flight);
	}
	
	//Read
	@GetMapping("/all")
	public ResponseEntity<List<Flight>> readAllFlights() {
		
		List<Flight> flights = service.readAll();
		if(flights.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(flights);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Flight> readFlightById(@PathVariable int id) {
		
		Flight flight = service.readById(id).orElseThrow(IdNotFoundException::new);
		return ResponseEntity.ok(flight);
	}
	
	//Update
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateFlight(@PathVariable int id, @RequestBody Flight flight) {
		
		//Check if path id = id
		if(id != flight.getId()) {
			throw new IdMismatchException();
		}
		//Check if the record to update exists
		Flight temp = service.readById(id).orElseThrow(IdNotFoundException::new);
		service.save(flight);
		return ResponseEntity.ok("Flight updated successfully");
	}
	
	//Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteFlight(@PathVariable int id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
