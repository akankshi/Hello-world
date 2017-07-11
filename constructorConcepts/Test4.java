package com.constructorConcepts;

class Animal5{
	Animal5(){
		System.out.println("Animal5 Default");
	}
	
	Animal5(String name){
		System.out.println("Animal5 overload1");
	}
	
	public void go(){
		System.out.println("Animal5 go");
	}
}

class Horse5 extends Animal5{
	Horse5(){
		System.out.println("Horse5 Default");
	}
	Horse5(String name){
		System.out.println("Horse5 overload1");
	}
	public void goHorse(){
		System.out.println("Horse5 go");
	}
}
public class Test4 extends Horse5 {

	Test4(){
		System.out.println("Test4 Default");
	}
	Test4(String name){
		go();
		System.out.println("Test4 overload1");
	}
	Test4(Animal5 animal5){
		super("af");  //note
		go();
		System.out.println("Test4 overload animal ref");
	}
	public void goTest4(){
		System.out.println("Test4 go");
	}
	
	public static void main(String args[]){
	
		Test4 obj=new Test4(new Animal5("ak"));  //note
	}
}
