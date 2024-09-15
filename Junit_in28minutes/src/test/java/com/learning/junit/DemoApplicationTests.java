package com.learning.junit;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.assertj.core.api.AssertProvider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.learning.junit.service.BussinessService;
import com.learning.junit.service.DataService;

@SpringBootTest
class DemoApplicationTests {
	
	//For removing error I did this
	BussinessService bs;
	@Test
	void test_with_stub() {
//		BussinessService bs = new BussinessService(new MyDataService());
		int result = bs.findTheGreatestFromAllData();
		assertThat(result).isEqualTo(56);
	}
	
	@Test
	void test_with_stub1() {
//		BussinessService bs = new BussinessService(new MyDataService1());
		int result = bs.findTheGreatestFromAllData();
		assertThat(result).isEqualTo(12);
	}
	

}


class MyDataService implements DataService {

	@Override
	public int[] retrieveAllData() {
		// TODO Auto-generated method stub
		return new int[] {12,34,56,7};
	}

	@Override
	public List<String> getNames() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

class MyDataService1 implements DataService {

	@Override
	public int[] retrieveAllData() {
		// TODO Auto-generated method stub
		return new int[] {12};
	}

	@Override
	public List<String> getNames() {
		// TODO Auto-generated method stub
		return null;
	}
	
}