package com.test.expamples;

import java.util.Arrays;

public class MyMath {
	public int sum(int[] arr) {
		return Arrays.stream(arr).parallel().sum();
	}
}