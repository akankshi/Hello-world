package com.castingConcepts;

class Man{
	
}
public class Test4 extends Man{

	public static void main(String args[]){
		
		//Test4 obj1= new Man();  //compile issue
		
		Test4 obj1= (Test4)new Man(); //clas cast
	}
}
