package com.overloadingOverridding;


/**Overloading and Overriding concepts**/

class Animal1{
	public void eat(Horse1 animal){
		System.out.println("Animal eat");
	}
}

class Horse1 extends Animal1{
	public void eat(Horse1 animal){
		System.out.println("Horse eat hay");
	}
	
	public void eat(Animal1 animal){
		System.out.println("Horse eat grass");
	}
}
public class Test3{
	
	public static void main(String args[]){
		Animal1 obj1=new Animal1();	
		Horse1 obj2=new Horse1();
	
		obj1.eat(obj2);
		/*obj1.eat(obj1);*/
		
		obj2.eat(obj1);
		obj2.eat(obj2);
		
		Animal1 obj3=new Horse1();
		/*obj3.eat(obj1);*/
		obj3.eat(obj2);
		
		
		
	}
	

}
