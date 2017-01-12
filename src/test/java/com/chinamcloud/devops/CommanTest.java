package com.chinamcloud.devops;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.chinamcloud.devops.utils.Collections3;

public class CommanTest {

	@Test
	public void test() {

		List<Integer> nums = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		nums.stream().filter(i -> i != 0).forEach(i -> System.out.println(i));
		
		System.out.println(Collections3.convertToString(nums, ","));

	}

}
