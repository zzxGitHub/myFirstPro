package com.example.demo.utils;

import java.util.ArrayList;
import java.util.HashSet;

public class Test {
	
	public int[] twoSum(int[] nums, int target) {
		int[] nums1 = { 2, 2, 7, 7, 11, 15 };
		nums = nums1;
		target = 9;
		ArrayList<Integer> re = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					re.add(i);
					re.add(j);
				}
			}
		}
		//去重
		ArrayList<Integer> listNew=new ArrayList<>(new HashSet(re));
		int[] result = new int[listNew.size()];
		for(int i = 0;i<listNew.size();i++) {
			result[i] = listNew.get(i);
			System.out.println(result[i]);
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		Test t = new Test();
		int[] nums1 = { 2, 7, 11, 15 };
		t.twoSum(nums1,9);
	}
}
