package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class TestBean {
	static final Log log = LogFactory.getLog(TestBean.class);

	public void test() {
		log.info("Hey I am in test method, Thanks for calling me");
	}
	
	 {
		log.info("Hello world test bean is initialized successfully");
	}
}
