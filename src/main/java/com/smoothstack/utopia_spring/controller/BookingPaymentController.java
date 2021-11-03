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

import com.smoothstack.utopia_spring.entity.BookingPayment;
import com.smoothstack.utopia_spring.exception.IdMismatchException;
import com.smoothstack.utopia_spring.exception.IdNotFoundException;
import com.smoothstack.utopia_spring.service.BookingPaymentService;

@RestController
@RequestMapping(path = "/payment")
public class BookingPaymentController {

	@Autowired
	private BookingPaymentService service;
	
	public BookingPaymentController(BookingPaymentService service) {
		
		this.service = service;
	}
	
	//Create
	@PostMapping
	public ResponseEntity<BookingPayment> addBookingPayment(@RequestBody BookingPayment bookingPayment) {
		service.save(bookingPayment);
		return ResponseEntity.ok(bookingPayment);
	}
	
	//Read
	@GetMapping
	public ResponseEntity<List<BookingPayment>> readAllBookingPayments() {
		
		List<BookingPayment> bookingPayments = service.readAll();
		if(bookingPayments.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(bookingPayments);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookingPayment> readBookingPaymentById(@PathVariable int id) {
		
		BookingPayment bookingPayment = service.readById(id).orElseThrow(IdNotFoundException::new);
		return ResponseEntity.ok(bookingPayment);
	}
	
	//Update
	@PutMapping("/{id}")
	public ResponseEntity<String> updateBookingPayment(@PathVariable int id, @RequestBody BookingPayment bookingPayment) {
		
		if(id != bookingPayment.getBookingId()) {
			throw new IdMismatchException();
		}
		service.save(bookingPayment);
		return ResponseEntity.ok("BookingPayment updated successfully");
	}
	
	//Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBookingPayment(@PathVariable int id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
