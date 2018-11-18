package com.xjx.jdktest.deginpattern;

public abstract class Duck {
	private String color;
	private int weight;
	public void  swim(){
		System.out.println("Duck swim");
		System.out.println(this.weight);
	}
	
	public void  swim2(){
		System.out.println("Duck swim2");
	}
	
	public void setWeight(int weight){
		this.weight = weight;
	}
	
	public int getWeight(){
		return this.weight;
	}
}
