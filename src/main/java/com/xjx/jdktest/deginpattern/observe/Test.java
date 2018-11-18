
package com.xjx.jdktest.deginpattern.observe;

public class Test {
	public static void main(String[] args) {
		Subject subject  = new ConcreteSubject();
		
		Observer zhangsan = new ConcreteObserver("zhangsan");
		Observer lisi = new ConcreteObserver("lisi");
		Observer wangwu  = new ConcreteObserver("wangwu");
		subject.registerObserver(zhangsan);
		subject.registerObserver(lisi);
		subject.registerObserver(wangwu);
		subject.setMessage("java是世界上最好的语言");
		
		System.out.println("----------------------------------------------");
		
		subject.removeObserver(zhangsan);
		subject.setMessage("python==========");
	}
}	
