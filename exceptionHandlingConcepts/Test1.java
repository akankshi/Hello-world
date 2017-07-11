package com.exceptionHandlingConcepts;

public class Test1 {

	public static void main(String args[]){		
			doStuff();
	}
	
	static void  doStuff(){
		mathStuff();
	}
	
	static void mathStuff(){
		try{
			int x=5/0;
			System.out.println("EXCEPTION CREATING BLOCK OCCURED ");
		}catch(Exception e){
			System.out.println("CATCH BLOCK ");
		}
		finally{
			System.out.println("EXCEPTION HAS OCCURED ");
		}
	
	}
}


