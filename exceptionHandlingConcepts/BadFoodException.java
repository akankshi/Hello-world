package com.exceptionHandlingConcepts;

public class BadFoodException extends Exception{

	public static void main(String args[]){
		
		System.out.println("THE input is : "+ args[0]);
		try {
			checkFood(args[0]);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	static void checkFood(String food) throws Exception{
		if(!food.equals("burger")){
			throw new Exception();
		}
	}
}
