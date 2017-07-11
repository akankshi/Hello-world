package com.initializationBlocksConcepts;


class Fire{
	
	Fire(){
		System.out.println("Fire default constructor");
	}
}
public class Test4 extends Fire {

	static{
		System.out.println("Test4 first static block");
	}
	
	{
		System.out.println("Test4 first instance block");
	}
	
	{
		System.out.println("Test4 second instance block");
	}
	
	static{
		System.out.println("Test4 second static block");
	}
	
	Test4 (){
		System.out.println("Test4 default constructor");
	}
	Test4 (String abc){
		System.out.println("Test4 overload constructor");
	}
	public static void main(String args[]){
		
		Test4 test4= new Test4();
	}
}
