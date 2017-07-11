package com.overloadingOverridding;


/**Overloading and Overriding concepts**/
class Animal2{
	public void makeNoise(){
		System.out.println("Animal noise");
	}
}

class Dog2 extends Animal2{
	
	public void makeNoise(){
		System.out.println("Bark");
	}
	
	public void playGame(){
		System.out.println("Play Dog");
	}
	
}
public class Test4 {
	
	public static void main(String args[]){
		Animal2[] animals={new Animal2(),new Dog2(),new Animal2()};
		
		for(Animal2 animal:animals){
			 animal.makeNoise();
			 
			/* Dog2 dog=(Dog2)animal;
			 dog.playGame();*/
			 
			 if(animal instanceof Dog2){				 
				 Dog2 dog=(Dog2)animal;
				 dog.playGame();
			 }
			
			
		}
		
	}

}
