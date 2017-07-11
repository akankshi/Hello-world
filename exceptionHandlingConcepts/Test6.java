package com.exceptionHandlingConcepts;

import java.io.FileOutputStream;

public class Test6 {

	public static void main(String args[]){
		
		System.out.println("MAIN STARTED");
		func1();	
		
	}
	
	static void func1()  {
		System.out.println("func1 STARTED");

		//throw new myCustomException1(); //note this a runtiome exc not checked.Hence, throws / try catch not required.
	}
}
