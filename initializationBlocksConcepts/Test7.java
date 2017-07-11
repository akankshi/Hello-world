package com.initializationBlocksConcepts;


class Fire2{
	
	Fire2(){
		System.out.println("Fire2 default constructor");
	}
	
	static{
		System.out.println("Fire2 first static block");
	}
	
	{
		System.out.println("Fire2 first instance block");
	}
}
public class Test7 extends Fire2 {

	static{
		System.out.println("Test7 first static block");
	}
	
	{
		System.out.println("Test7 first instance block");
	}
	
	{
		System.out.println("Test7 second instance block");
	}
	
	static{
		System.out.println("Test7 second static block");
	}
	
	Test7 (){
		System.out.println("Test7 default constructor");
	}
	Test7 (String abc){
		System.out.println("Test7 overload constructor");
	}
	public static void main(String args[]){
		
	/*	Test7 Test7= new Test7();*/
		
		Test7 test=new Test7();
	}
}
