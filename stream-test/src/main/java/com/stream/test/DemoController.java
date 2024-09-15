package com.stream.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {

	@ExceptionHandler()
	public ResponseEntity<Object> exceptionHandler(Exception ex) {
		
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR).ok("bhai code phat gya demo controller me");
	}
	
	@GetMapping
	public ResponseEntity<Object> check() {
		if(1 == new Integer(1)) {
			throw new NullPointerException();
		}
		return new ResponseEntity<>(HttpStatus.OK).ok("Hello World");
	}
}
