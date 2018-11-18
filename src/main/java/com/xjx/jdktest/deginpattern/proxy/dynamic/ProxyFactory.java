package com.xjx.jdktest.deginpattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
	
	private Object target;
	
	public  ProxyFactory(Object target){
		this.target =target;
	}
	
	public Object getProxyInstance(){
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						 // 在目标对象的方法执行之前简单的打印一下  
				        System.out.println("------------------before------------------");  
						Object result =method.invoke(target, args);
						 System.out.println("------------------before------------------");  
						 return result;
					}
				});
	}
}
