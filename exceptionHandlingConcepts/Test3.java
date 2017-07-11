package com.exceptionHandlingConcepts;

public class Test3 {

	public static void main(String args[]){
		
		Test3 test3=new Test3();
		String result=test3.func1();
		System.out.print("ouput is " + result);
		
	}
	
	String func1(){
		String abc="try";
		try {
			
			throw new Exception();
		
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			//abc="catch";
			return "catch block";
		}
		finally{
			return "finally block";
			//abc="finally";
		}
	
	}
}
