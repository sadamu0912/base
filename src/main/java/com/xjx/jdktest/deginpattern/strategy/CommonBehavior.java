package com.xjx.jdktest.deginpattern.strategy;

import com.alibaba.fastjson.JSON;

public class CommonBehavior {
	public void print(){
		System.out.print("============do common beahavior before===============");
		User user = getCurrentUser();
		System.out.print(JSON.toJSON(user));
	}
	
	private User getCurrentUser() {
		User user = new User();
		user.setUserId("user111111");
		user.setUserName("zhangsan");
		Role role = new Role("role111111","ADMIN");
		user.setRole(role);
		return user;
	}
}
