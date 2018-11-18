package com.xjx.jdktest.deginpattern.observe;


public interface Subject {
	
	
	public void registerObserver(Observer obs);
	
	public void removeObserver(Observer obs);
	
	public void notifyObservers();
	
	public void setMessage(String s);
}
