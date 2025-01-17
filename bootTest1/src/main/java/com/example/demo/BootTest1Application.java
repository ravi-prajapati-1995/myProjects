package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;

import com.ravi.Test;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ravi"})
public class BootTest1Application {

	@Autowired(required = false)
	@Lazy(value = false)
	TestBean testBean;
	
	@Autowired
	private ApplicationContext context;
	
	public static void main(String[] args) {
		SpringApplication.run(BootTest1Application.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	void run() {
		Test test = context.getBean(Test.class);

		System.out.println(testBean);
	}
	
	
	


}
