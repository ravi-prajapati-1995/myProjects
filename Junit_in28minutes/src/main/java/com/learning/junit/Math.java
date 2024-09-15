package com.learning.junit;

import java.util.Arrays;

public class Math {
	public int sum(int[] arr) {
		return Arrays.stream(arr).sum();
	}
}
