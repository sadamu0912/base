package com.xjx.jdktest;

import java.util.HashSet;

public class Time33Test {
	public static void main(String[] args) {
		
		HashSet<Item> hashSet = new HashSet<Item>();  
		long start;  
		long end;  
		String[] strs = { "Alvin", "Chan", "HashSet", "WOW" };  
		String[] tmp;  
		int count = 0;  
		      
		start = System.currentTimeMillis();  
		  
		for (int i = 0; i < 3000000; i++) {  
		    tmp = new String[4];  
		    for (int j = 0; j < 4; j++) {  
		        // 用strs中随机抽取字符串，并加入随机数，生成tmp字符串数组  
		        tmp[j] = strs[(int) (Math.random() * 3)] + (int) (Math.random() * i);  
		    }  
		    // 加入无法插入到hashSet中，计数器加1  
		    if(!hashSet.add(new Item(tmp))) {  
		        count++;  
		    }  
		}  
		  
		end = System.currentTimeMillis();  
		  
		System.out.println("插入300,0000条记录");  
		System.out.println("所需时间：" + (end - start) + " ms");  
		System.out.println("插入个数：" + hashSet.size());  
		System.out.println("失败次数：" + count);  
	}
}
