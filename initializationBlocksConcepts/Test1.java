package com.initializationBlocksConcepts;

public class Test1 {
	
	static{
		System.out.println("Test1 first static block");
	}
	
	{
		System.out.println("Test1 first instance block");
	}
	
	{
		System.out.println("Test1 second instance block");
	}
	
	static{
		System.out.println("Test1 second static block");
	}
	
	public static void main(String args[]){
		
	}

}
