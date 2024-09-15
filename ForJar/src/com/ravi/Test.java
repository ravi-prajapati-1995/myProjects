package com.ravi;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;

import org.springframework.stereotype.Component;

@Component
public class Test {
	static final Logger log = System.getLogger(Test.class.getName());
	
	public void test() {
		System.out.println("I am in test method this is syso");
		log.log(Level.ERROR,"I am in test method this is log");
	}
	
	{
		log.log(Level.ERROR," Bhai meri class ho gyi hai load jvm me mubark ho bsdk");
	}
}
