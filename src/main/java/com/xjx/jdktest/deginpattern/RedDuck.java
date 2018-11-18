package com.xjx.jdktest.deginpattern;

public class RedDuck extends Duck implements Cloneable{
	public void  swim2(){
		System.out.println("RedDuck swim2");
		this.swim();
		super.swim2();
	}
	
	protected void swim3(){
		System.out.println("RedDuck swim3");
	}
	
	@Override  
	public Object clone(){
		RedDuck redDuck = null;
		try{
			redDuck = (RedDuck) super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return redDuck;
	}
	
}
