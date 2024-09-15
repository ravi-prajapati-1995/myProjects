package com.ravi.sort;

import java.util.List;

import com.ravi.sort.algo.BubbleSort;

public class MySortinUtil {
	public List<String> sort(List<String> list){
		BubbleSort bs = new BubbleSort();
		return bs.sort(list);
	}
}
