package com.stream.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public Thread getThread() {
		Thread th = new Thread();
		th.setName("My_bean_thread");
		
		return th;
	}
}
