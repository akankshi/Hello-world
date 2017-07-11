package com.initializationBlocksConcepts;


class Fire1{
	
	Fire1(){
		System.out.println("Fire1 default constructor");
	}
	
	static{
		System.out.println("Fire1 first static block");
	}
}
public class Test5 extends Fire1 {

	static{
		System.out.println("Test5 first static block");
	}
	
	{
		System.out.println("Test5 first instance block");
	}
	
	{
		System.out.println("Test5 second instance block");
	}
	
	static{
		System.out.println("Test5 second static block");
	}
	
	Test5 (){
		System.out.println("Test5 default constructor");
	}
	Test5 (String abc){
		System.out.println("Test5 overload constructor");
	}
	public static void main(String args[]){
		
	/*	Test5 test5= new Test5();*/
		
		Test5 test=new Test5();
	}
}
