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

import com.smoothstack.utopia_spring.entity.BookingGuest;
import com.smoothstack.utopia_spring.exception.IdMismatchException;
import com.smoothstack.utopia_spring.exception.IdNotFoundException;
import com.smoothstack.utopia_spring.service.BookingGuestService;

@RestController
@RequestMapping(path = "/guest")
public class BookingGuestController {

	@Autowired
	private BookingGuestService service;
	
	public BookingGuestController(BookingGuestService service) {
		
		this.service = service;
	}
	
	//Create
	@PostMapping
	public ResponseEntity<BookingGuest> addBookingGuest(@RequestBody BookingGuest bookingGuest) {
		service.save(bookingGuest);
		return ResponseEntity.ok(bookingGuest);
	}
	
	//Read
	@GetMapping
	public ResponseEntity<List<BookingGuest>> readAllBookingGuests() {
		
		List<BookingGuest> bookingGuests = service.readAll();
		if(bookingGuests.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(bookingGuests);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookingGuest> readBookingGuestById(@PathVariable int id) {
		
		BookingGuest bookingGuest = service.readById(id).orElseThrow(IdNotFoundException::new);
		return ResponseEntity.ok(bookingGuest);
	}
	
	//Update
	@PutMapping("/{id}")
	public ResponseEntity<String> updateBookingGuest(@PathVariable int id, @RequestBody BookingGuest bookingGuest) {
		
		if(id != bookingGuest.getUserId()) {
			throw new IdMismatchException();
		}
		service.save(bookingGuest);
		return ResponseEntity.ok("BookingGuest updated successfully");
	}
	
	//Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBookingGuest(@PathVariable int id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
