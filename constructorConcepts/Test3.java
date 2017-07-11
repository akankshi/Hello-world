package com.constructorConcepts;

class Animal4{
	Animal4(){
		System.out.println("Animal4 Default");
	}
	
	Animal4(String name){
		System.out.println("Animal4 overload1");
	}
	
	public void go(){
		System.out.println("Animal4 go");
	}
}

class Horse4 extends Animal4{
	Horse4(){
		System.out.println("Horse4 Default");
	}
	Horse4(String name){
		System.out.println("Horse4 overload1");
	}
	public void goHorse(){
		System.out.println("Horse4 go");
	}
}
public class Test3 extends Horse4 {

	Test3(){
		System.out.println("Test2 Default");
	}
	Test3(String name){
		go();
		System.out.println("Test2 overload1");
	}
	public void goTest3(){
		System.out.println("Test3 go");
	}
	
	public static void main(String args[]){
	
		Test3 obj=new Test3("");
	}
}
