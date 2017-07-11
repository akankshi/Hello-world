package com.exceptionHandlingConcepts;

interface likings{
	
	}
abstract class  human{
	
	int age=18;
	human(){
		
	}
	void mood(){
		System.out.print("happy");
	}
	
	abstract void favFood();
	
}
public class Test9 extends human {
	
	public static void main(String args[]){
		
		Test9 ww=new Test9();
		System.out.print("age is " + ww.age);
	}

	@Override
	void favFood() {	
		
	}
}
