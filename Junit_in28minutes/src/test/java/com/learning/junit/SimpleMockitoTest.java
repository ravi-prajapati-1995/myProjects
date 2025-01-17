package com.learning.junit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.learning.junit.service.BussinessService;
import com.learning.junit.service.DataService;

@ExtendWith(MockitoExtension.class)
class SimpleMockitoTest {
	
	@Mock
	DataService dataServiceMock;
	
	@InjectMocks
	BussinessService bussinessService;

	@Test
	void test() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1,2,4});
		int result = bussinessService.findTheGreatestFromAllData();
//		assertThat(result).isEqualTo(4);
		
		Assertions.assertEquals(result, 4);
	}
	
	@Test
	void One_value_test() {
		
//		DataService dataServiceMock = mock(DataService.class);
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{4});
//		BussinessService bs = new BussinessService(dataServiceMock);
		
		int result = bussinessService.findTheGreatestFromAllData();
		assertThat(result).isEqualTo(4);
	}
	
	@Test
//	@Disabled("Until I didn't come tommorrow for call")
	void no_value_test() {
		
//		DataService dataServiceMock = mock(DataService.class);
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});
		
		assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> {
			int result = bussinessService.findTheGreatestFromAllData();
		});
	}
	
	@Test
	void static_mock() {
		try(MockedStatic<DataService> mocked = mockStatic(DataService.class)) {
			mocked.when(DataService::sayHello).thenReturn("Hello world");
			System.out.println(DataService.sayHello());
			mocked.verify(DataService::sayHello);
		}
		
	}

}
