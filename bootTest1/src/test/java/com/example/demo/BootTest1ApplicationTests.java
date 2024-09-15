package com.example.demo;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
class BootTest1ApplicationTests {
	
	@Autowired
	Environment env;
	
	@Value("${state}")
	private String spelValue;

	@Test
	void contextLoads() {
		System.out.println(env.getProperty("name"));
		System.out.println(spelValue);
		
		Set<Integer> set = new HashSet<>();
//		Integer.pars
	}

}
