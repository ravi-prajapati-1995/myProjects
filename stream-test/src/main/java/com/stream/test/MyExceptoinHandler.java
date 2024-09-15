package com.stream.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptoinHandler {
	
	@ExceptionHandler()
	public ResponseEntity<Object> exceptionHandler(Exception ex) {
		
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR).ok("bhai code phat gya");
	}
}
