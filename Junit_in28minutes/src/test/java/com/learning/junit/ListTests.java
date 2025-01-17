package com.learning.junit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ListTests {

	@Test
	void listTest() {
		List<String> listMock = mock(List.class);
		when(listMock.size()).thenReturn(5).thenReturn(3);
		
		assertEquals(listMock.size(), 5);
		assertEquals(listMock.size(), 3);
		
//		Mockito.mockstati
	}
	
	
	@Test
	void listTest1() {
		List<String> listMock = mock(List.class);
		when(listMock.get(0)).thenReturn("Ravi").thenReturn("Nitin");
		
		assertEquals(listMock.get(0), "Ravi");
		assertEquals(listMock.get(0), "Nitin");
	}
	
	@Test
	void listTest2() {
		List<String> listMock = mock(List.class);
		when(listMock.get(Mockito.anyInt())).thenReturn("Ravi");
		
		
		assertEquals(listMock.get(444), "Ravi");
//		assertEquals(listMock.get(1), "Nitin");
		
//		System.out.println(listMock.get(0)+"\t"+listMock.get(1));
	}
	
	

}
