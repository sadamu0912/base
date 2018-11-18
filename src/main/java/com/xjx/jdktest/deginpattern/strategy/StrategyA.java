package com.xjx.jdktest.deginpattern.strategy;

public class StrategyA implements BaseStrategy{

	
	@Override
	public void doSomeThing() {
		commonBeofre.print();
		System.out.println("strategyA doSomeThing==========");
		commonAfter.printAfter();
	}

}
