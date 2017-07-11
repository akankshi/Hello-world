package com.constructorConcepts;

class Hanger{
	
	Hanger(){
		System.out.println("Hanger Default");
	}
	
	Hanger(String name){
		System.out.println("Hanger overload1");
	}
	
}

public class Test9 extends Hanger {
	
	Test9(){		
		this("hh");
	//super();
		System.out.println("Test9 Default");
	}
	
	Test9(String name){
		System.out.println("Test9 overload1");
	}
	
	public static void main(String args[]){
		
		Test9 obj1=new Test9();
		
	}

}
