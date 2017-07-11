package com.staticConcepts;


class Person{
	
	static int s=5;
	static void go(){
		System.out.println("Person go");
	}
}
public class Test4 extends Person {

	static int s=6;
	
	static void go(){
		System.out.println("Test4 go");
	}
	public static void main(String args[]){			
	System.out.println("value:" + s);	
	go();
	
	Person pp=new Test4();
	pp.go();
	}
}
