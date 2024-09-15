package com.ravi.consumer;

import java.util.List;
import java.util.logging.Logger;

import com.ravi.sort.MySortinUtil;

public class MyConsumer {
	private static Logger log = Logger.getLogger(MyConsumer.class.getName());
	public static void main(String[] args) {
		
		MySortinUtil msu = new MySortinUtil();
		List<String> li = msu.sort(List.of("Ravi","Nitin","Roni","Lakhvir","Rupin","Nimrat"));
		
		log.info(li.toString());
	
	}
	
}
