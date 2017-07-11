package com.exceptionHandlingConcepts;
//assertions 
public class Test8 {

	public static void main(String args[]){
		
		System.out.println("entered main");
		doStuff(5,6);
	}
	
	static void doStuff(int a,int b){
		System.out.println("enetred doStuff");
		//boolean assert=false;
	/*	assert(a>b):3;*/
		
		//assert(a>b):;
		assert(a>b):a=9;
		System.out.println("exit doStuff");
	
}
	
	
	}
