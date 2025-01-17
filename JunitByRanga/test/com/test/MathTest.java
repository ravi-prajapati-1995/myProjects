package com.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import com.test.expamples.MyMath;

class MathTest {
	MyMath math = new MyMath();
	
	@Test
	void sum_of_three_number() {
		assertEquals(10,math.sum(new int[] {1,3,4,2}));
	}
	
	@Test
	void sum_of_empty_array() {
		assertEquals(0,math.sum(new int[] {}));
	}
	
	@Test
	void sum_of_10_lakh_numbers() {
		Random random = new Random();
		
		assertEquals(100000, IntStream.iterate(1, a -> random.nextInt(10000000)).limit(10000000).toArray());
	}

}
