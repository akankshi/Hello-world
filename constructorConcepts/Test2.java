package com.constructorConcepts;

class Animal3{
	Animal3(){
		System.out.println("Animal3 Default");
	}
	
	Animal3(String name){
		System.out.println("Animal3 overload1");
	}
}

class Horse3 extends Animal3{
	Horse3(){
		System.out.println("Horse3 Default");
	}
	Horse3(String name){
		System.out.println("Horse3 overload1");
	}
}
public class Test2 extends Horse3 {

	Test2(){
		System.out.println("Test2 Default");
	}
	Test2(String name){
		System.out.println("Test2 overload1");
	}
	
	public static void main(String args[]){
		
		Test2 obj1=new Test2();
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>");
		Test2 obj2=new Test2("Akanksha");
	}
}
