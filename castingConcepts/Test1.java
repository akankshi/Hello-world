package com.castingConcepts;


class Animal1{
	
	
}


public class Test1 extends Animal1 {
	
	public static void main(String args[]){
		
		Animal1[] animals={new Animal1(),new Test1()};
		
		for(Animal1 animal:animals){
			
	Test1 tst1=(Test1)animal;
		}
	}

}
