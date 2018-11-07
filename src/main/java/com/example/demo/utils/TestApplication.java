package com.example.demo.utils;

import java.util.Arrays;

public class TestApplication {

	/**
	 * 简单选择排序
	 * @param a
	 */
	public void selectSort(int[] a) {
		int temp = 0;
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < a[i]) {
					temp = a[j];
					a[j] = a[i];
					a[i] = temp;
				}
			}
			int num = i+1;
			System.out.println("第"+num+"次"+Arrays.toString(a));
		}
	}
	
	/**
	 * 二路归并排序
	 * @param args
	 */
	private static void mergearray(int[] array, int start, int middle, int end,int[] tmp) {

        int first = start;
        int second = middle + 1;
        int index = start;


        while ((first <= middle) && (second <= end)) {
            if (array[first] >= array[second])
                tmp[index++] = array[second++];
            else
                tmp[index++] = array[first++];
        }
        while (first <= middle)
            tmp[index++] = array[first++];
        while (second <= end)
            tmp[index++] = array[second++];

        for (first = start; first <= end; first++)
            array[first] = tmp[first];
        
        
        System.out.println("merge is "+Arrays.toString(array));

    }
	
	public static void mergesort(int[] array, int start, int end,int[] tmp) {

        if (start >= end)
            return;
        int middle = ((end + start) >> 1);
        mergesort(array, start, middle,tmp);// 递归划分左边的数组
        mergesort(array, middle + 1, end,tmp);// 递归划分右边的数组
        mergearray(array, start, middle, end,tmp);// 对有序的两个数组进行合并成一个有序的数组
    }

	public static void main(String[] args) {
		/*TestApplication app = new TestApplication();
		int[] a = {24,62,36,19,47,55};
		System.out.println(Arrays.toString(a));
		app.selectSort(a);*/
		int[] a = {43,18,60,26,31,54};
		mergesort(a,0,a.length-1,a);
	}
}
