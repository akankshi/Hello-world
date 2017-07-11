package com.staticConcepts;


class MadPerson{
	
	static int s=5;
	static void go(){
		System.out.println("MadPerson go");
	}
}
public class Test5 extends MadPerson {


	
	public static void main(String args[]){			
	System.out.println("value:" + s);	
	go();
	
	}
}
