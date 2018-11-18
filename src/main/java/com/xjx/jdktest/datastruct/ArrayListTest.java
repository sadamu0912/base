package com.xjx.jdktest.datastruct;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.fastjson.util.IOUtils;

public class ArrayListTest {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		List<String> list =new ArrayList<String>();
		list.add("123");
		list.add("124");
		list.add("125");
		list.add("126");
		list.add("127");
		list.add(2, "138");
		AtomicInteger a = new AtomicInteger(3);
		 List<String> stringList = new ArrayList<String>();
	        stringList.add("hello");
	        stringList.add("world");
	        stringList.add("hollis");
	        stringList.add("chuang");
	        System.out.println("init StringList" + stringList);
	        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("stringlist"));
	        objectOutputStream.writeObject(stringList);
	 
	        IOUtils.close(objectOutputStream);
	        File file = new File("stringlist");
	        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
	        List<String> newStringList = (List<String>)objectInputStream.readObject();
	        IOUtils.close(objectInputStream);
	        if(file.exists()){
	            file.delete();
	        }
	        System.out.println("new StringList" + newStringList);
		
	}
}
