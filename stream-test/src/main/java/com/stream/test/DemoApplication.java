package com.stream.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private Thread thread;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	
//	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");
			Iterable<Employee> it = employeeRepo.findAll();
			
			List<Employee> li = new ArrayList<>();
			it.forEach(li::add);
			
			li.stream().filter(emp -> emp.getSalary() > 15000).forEach(System.out::println);
			
			System.out.println(thread.getName());
			
		};
	}
}
