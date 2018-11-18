package com.xjx.jdktest.deginpattern.proxy.staticmode;

public class Test {
	public static void main(String[] args) {
		UserDao userdao = new UserDao();
		UserDaoProxy proxy =  new UserDaoProxy(userdao);
		proxy.save();
	}
}
