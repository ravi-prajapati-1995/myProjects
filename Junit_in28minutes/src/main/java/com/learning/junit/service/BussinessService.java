package com.learning.junit.service;

import java.util.Arrays;

public class BussinessService {
	
	private DataService dataService;



	public int findTheGreatestFromAllData() {
		int[] data = dataService.retrieveAllData();	
		return Arrays.stream(data).max().getAsInt();
		
	}

}
