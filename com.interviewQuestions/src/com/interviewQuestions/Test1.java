package com.interviewQuestions;

public class Test1 {

	String abc= new String();
	
	
	@Override
	protected void finalize() throws Throwable {		
		System.out.println("Finalizeee block!!!!");
	}
	

	public static void main(String args[]){
		
		Test1 tt= new Test1();
	
		Test1 t2= new Test1();
		System.out.println("main block!!!!");
		
	}
	
}
