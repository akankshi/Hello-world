package com.abstractClassConcepts;

 abstract class Living{
	
	 Living(){}  //abstract class can have constructor
	void haveSenses(){
		System.out.println("Living have senses");
	}
	
     abstract void eatFood();
}
 
 class Human extends Living{

	@Override
	 void eatFood() {
		System.out.println("Human eats food");
		
	}
	 
 }

public class Test1 {
	
	public static void main(String args[]){		
		
		/*Living obj2=new Living();  // cannot instantiate an abstract class*/
		
		Living obj1;
		/*obj1.haveSenses();*/
		
		Human obj3=new Human();
		obj3.haveSenses();
		obj3.eatFood();
	}

}
