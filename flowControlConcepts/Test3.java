package com.flowControlConcepts;

public class Test3 {

	public static void main(String args[]){

		int i=3;
		switch(i){
		
		case 3:
			System.out.println("three");
		case 5: 
			System.out.println("five");
		case 7:
		
			/*System.out.println("seven");*/
			default:
				System.out.println("default");
				break;
			case 10:
				System.out.println("ten");	
		}
		
	char c='a';
	switch(c){
	case 'a':
		System.out.println("a");
	case 'v':
		System.out.println("v");
		
	
	}
	
	/*String abc="abc";
	switch(abc){
	
	}*/
	

	
	System.out.println("outside all");
		
	}
}
