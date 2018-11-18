package com.xjx.jdktest.deginpattern.strategy;

public class StrategyB implements BaseStrategy{
	@Override
	public void doSomeThing() {
		commonBeofre.print();
		System.out.println("strategyB doSomeThing==========");
		commonAfter.printAfter();
	}
}
