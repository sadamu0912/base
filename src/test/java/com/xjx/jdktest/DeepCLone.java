package com.xjx.jdktest;

import com.xjx.jdktest.base.Inner;
import com.xjx.jdktest.base.Outer;

public class DeepCLone {
	
	public static void main(String[] args) {
		Outer outer = new Outer();
		Inner inner = new Inner();
		inner.setName("abc");
		outer.setAge(3);
		outer.setInner(inner);
		Outer outer2 =outer.myclone();
	}
	
}
