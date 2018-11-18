package com.xjx.jdktest.deginpattern.strategy;

public class Test {
	public static void main(String[] args) {
		new StragegyExecutor(new StrategyA())
		.contextInterface();
	}
}
