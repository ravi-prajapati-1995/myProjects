package com.learning.junit.service;

import java.util.List;

public interface DataService {

	int[] retrieveAllData();
	
	List<String> getNames();
	
	static String sayHello() {
		return "say Hello";
	}
	
}