package com.initializationBlocksConcepts;


 public class Magic{
	 Magic(){
			System.out.println("Magic default constructor");
	 }
	 
	 static {
		 System.out.println("Magic static block");
	 }
	 
	 Magic(String xyz){
			System.out.println("Magic overload constructor");
	 }
}
 class Test3 {

	static{
		System.out.println("Test3 first static block");
	}
	
	{
		System.out.println("Test3 first instance block");
	}
	
	{
		System.out.println("Test3 second instance block");
	}
	
	static{
		System.out.println("Test3 second static block");
	}
	
	Test3 (){
		System.out.println("Test3 default constructor");
	}
	Test3 (String abc){
		System.out.println("Test3 overload constructor");
	}
	public static void main(String args[]){
		
		
	}
}
