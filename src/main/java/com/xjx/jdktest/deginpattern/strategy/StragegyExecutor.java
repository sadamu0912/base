package com.xjx.jdktest.deginpattern.strategy;

public class StragegyExecutor {
	private BaseStrategy strategy;
	
	public StragegyExecutor(BaseStrategy strategy){
        this.strategy = strategy;
    }
	
	/**
     * 策略方法
     */
    public void contextInterface(){
        
        strategy.doSomeThing();
    }
	
}
