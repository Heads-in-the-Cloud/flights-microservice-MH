package com.smoothstack.utopia_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = IdNotFoundException.class)
	public ResponseEntity<Object> exception(IdNotFoundException exception) {
		
		return new ResponseEntity<>("ID not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = IdMismatchException.class)
	public ResponseEntity<Object> exception(IdMismatchException exception) {
		
		return new ResponseEntity<>("IDs do not match", HttpStatus.CONFLICT);
	}
}
