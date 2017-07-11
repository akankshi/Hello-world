package com.constructorConcepts;

class Animal7{

	
	public void go(){
		System.out.println("Animal7 go");
	}
}

class Horse7 extends Animal7{

	public void goHorse(){
		System.out.println("Horse7 go");
	}
}
public class Test6 extends Horse7 {

	Test6(){
		System.out.println("Test6 Default");
	}

	public void goTest6(){
		System.out.println("Test6 go");
	}
	
	public static void main(String args[]){
	
		Test6 obj=new Test6();
	}
}


