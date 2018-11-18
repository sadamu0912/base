package com.xjx.jdktest.deginpattern.strategy;

public interface BaseStrategy {
	
	//抽出strategy类的公共部分
	public   CommonBehavior commonBeofre = new CommonBehavior();
	public   CommonAfterBehavior commonAfter = new CommonAfterBehavior();
	
	
	
	public abstract void doSomeThing();
}
