package com.meme.dronesMS.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class DroneExceptionController {

	@ExceptionHandler(value= DroneNotfoundException.class)
	public ResponseEntity<Object> exception(DroneNotfoundException exception){
		return new ResponseEntity<>("Drone not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value= DroneNotAvailableException.class)
	public ResponseEntity<Object> exception(DroneNotAvailableException exception){
		return new ResponseEntity<>("There is no available drones now", HttpStatus.NOT_FOUND);
	}
	
	 @ExceptionHandler(value = {ConstraintViolationException.class})
	 public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e, WebRequest request) {
	        return new ResponseEntity<>("Validation Error..", HttpStatus.BAD_REQUEST);
	    }
}
