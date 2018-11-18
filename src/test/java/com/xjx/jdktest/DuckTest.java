package com.xjx.jdktest;

import com.xjx.jdktest.deginpattern.RedDuck;

public class DuckTest {
	public static void main(String[] args) {
		RedDuck duck = new RedDuck();
		duck.setWeight(5);
		RedDuck duck2 = (RedDuck) duck.clone();
		duck2.setWeight(6);
		System.out.println(duck2.getWeight());
		System.out.println(duck.getWeight());
	}
}
