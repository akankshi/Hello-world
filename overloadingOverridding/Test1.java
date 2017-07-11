package com.overloadingOverridding;

import java.io.IOException;

/**Overloading and Overriding concepts-access specifiers,exception throwing**/
class Test2{
	
  protected int calSum(int number1){
		
		return number1+1;
	}
/*  private int calSum(int number1){  //when private, subclass does not inherit it,so this method cannot be overridden
		
		return number1+1;
	}*/
}
public class Test1 extends Test2 {
	
	protected int calSum(int number1){  //if superclass method is private, then this method is not an overridden method but a new method
		
		return number1+2;
	}
	
	/*  protected int calSum(int number1) throws IOException{
	
	return number1+2;
}
*/
	
	protected int calSum(double number1){
		
		return (int)(number1+2);
	} 
	
	
	public static void main(String args[]){
		
		Test2 test2=new Test1();
		System.out.println(test2.calSum(4));
	/*	System.out.println(test2.calSum(4.0));*/
		
	}
}
