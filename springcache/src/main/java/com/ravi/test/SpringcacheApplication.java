package com.ravi.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class SpringcacheApplication  {
	
	public static final Logger log = Logger.getLogger(SpringcacheApplication.class.getName());

	@Autowired
	CacheManager cacheManager;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringcacheApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		log.log(Level.INFO, cacheManager.getCacheNames().toString());
	}
	
}
