package com.staticConcepts;

 class Food {
	
	static int instanceCount=0;
	Food(){
		instanceCount+=1;
	}
	

}

//how to access the static variable 
class Test2{
	
public static void main(String args[]){
		
		Food obj1=new Food();
		new Food();
		Food obj3=new Food();
		System.out.println("NUMBER OF INSTANCES>> 1:" + obj3.instanceCount); 
		Food obj4=new Food();
		
		System.out.println("NUMBER OF INSTANCES>> 2 :" + Food.instanceCount);  //using class name and dot operatr
		System.out.println("NUMBER OF INSTANCES>3 :" + obj3.instanceCount);  //just to confuse:using obj refrence name and dot operatr
		
		
	}
}