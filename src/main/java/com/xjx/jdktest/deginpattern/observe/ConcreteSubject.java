package com.xjx.jdktest.deginpattern.observe;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject{
	private List<Observer> list = new ArrayList<Observer>();
    private String message;
	
	@Override
	public void registerObserver(Observer obs) {
		list.add(obs);
	}

	@Override
	public void removeObserver(Observer obs) {
		list.remove(obs);
	}

	@Override
	public void notifyObservers() {
		for (Observer obj : list){
			obj.update(message);
		}
	}
	
	
	@Override
	public void setMessage(String s) {
		this.message = s;
		notifyObservers();
	}

}
