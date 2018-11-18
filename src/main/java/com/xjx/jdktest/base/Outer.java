package com.xjx.jdktest.base;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Outer implements Serializable{
	  private static final long serialVersionUID = 369285298572941L;  //最好是显式声明ID
	  private Inner inner;
	  private int age;
	//Discription:[深度复制方法,需要对象及对象所有的对象属性都实现序列化]　 
	/*  public Outer myclone() {
	      Outer outer = null;
	      try { // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
	          ByteArrayOutputStream baos = new ByteArrayOutputStream();
	          ObjectOutputStream oos = new ObjectOutputStream(baos);
	          oos.writeObject(this);// 将流序列化成对象
	          ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	          ObjectInputStream ois = new ObjectInputStream(bais);
	          outer = (Outer) ois.readObject();
	      } catch (IOException e) {
	          e.printStackTrace();
	      } catch (ClassNotFoundException e) {
	          e.printStackTrace();
	      }
	      return outer;
	  }
	  */
	  /**
	   * 序列化成json字符串
	   * @return
	   */
	  public Outer myclone() {
		  Outer outer =null;
		  JSONObject obj = new JSONObject(); 
		  obj.put("age", this.getAge());
		  obj.put("inner", this.getInner());
		  String json = obj.toJSONString();
		  outer = JSON.parseObject(json, Outer.class);
		  return outer;
	  }
	  
	  

	public Inner getInner() {
		return inner;
	}

	public void setInner(Inner inner) {
		this.inner = inner;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	  
	  
	}