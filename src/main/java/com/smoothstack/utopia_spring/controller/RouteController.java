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
import com.smoothstack.utopia_spring.entity.Route;
import com.smoothstack.utopia_spring.exception.*;
import com.smoothstack.utopia_spring.service.RouteService;

@RestController
@RequestMapping(path = "/route")
public class RouteController {

	@Autowired
	private RouteService service;
	
	public RouteController(RouteService service) {
		
		this.service = service;
	}
	
	//Create
	@PostMapping("/add")
	public ResponseEntity<Route> addRoute(@RequestBody Route route) {
		service.save(route);
		return ResponseEntity.ok(route);
	}
	
	//Read
	@GetMapping("/all")
	public ResponseEntity<List<Route>> readAllRoutes() {
		
		List<Route> routes = service.readAll();
		if(routes.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(routes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Route> readRouteById(@PathVariable int id) {
		
		Route route = service.readById(id).orElseThrow(IdNotFoundException::new);
		return ResponseEntity.ok(route);
	}
	
	//Update
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateRoute(@PathVariable int id, @RequestBody Route route) {
		
		//Check if path id = id
		if(id != route.getId()) {
			throw new IdMismatchException();
		}
		//Check if the record to update exists
		Route temp = service.readById(id).orElseThrow(IdNotFoundException::new);
		service.save(route);
		return ResponseEntity.ok("Route updated successfully");
	}
	
	//Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteRoute(@PathVariable int id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
