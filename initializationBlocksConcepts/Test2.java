package com.initializationBlocksConcepts;

public class Test2 {

	static{
		System.out.println("Test2 first static block");
	}
	
	{
		System.out.println("Test2 first instance block");
	}
	
	{
		System.out.println("Test2 second instance block");
	}
	
	static{
		System.out.println("Test2 second static block");
	}
	
	Test2 (){
		System.out.println("Test2 default constructor");
	}
	Test2 (String abc){
		System.out.println("Test2 overload constructor");
	}
	public static void main(String args[]){
		
		Test2 test1= new Test2();
	}
}
