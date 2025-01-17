package com.ravi.sort;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestClass {
	
	public static void main(String[] args) {
		System.out.println(sum());
		System.out.println(Set.of("rav").getClass());
		System.out.println(Map.ofEntries(Map.entry("Ravi","")).getClass());
	}

	static int sum(int a, int b) {
		System.out.println("with tow argument called");
		return a+b;
	}
	
	static int sum(int...a) {
		System.out.println("var args method called");
		return Arrays.stream(a).sum();
	}
}


abstract class Student {
	private Long id;
	private String name;
	private String email;
	
	
	Student(Long id, String name,String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public void printDetails() {
		System.out.println(String.format("Id: %s, Name: %s, email : %s", id,name,email));
	}
	
	abstract String getStudentName();
}


class UsStudent extends Student {

	UsStudent(Long id, String name, String email) {
		super(id, name, email);
	}

	@Override
	String getStudentName() {
		return this.getStudentName();
	}
	
}
