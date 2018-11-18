package com.xjx.jdktest.base;

import java.io.Serializable;

public class Inner implements Serializable{
	private static final long serialVersionUID = 369285298572942L;  //最好是显式声明ID
		 private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		} 
		 
}
