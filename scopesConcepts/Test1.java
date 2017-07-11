package com.scopesConcepts;

public class Test1 {

	int x; //instance variables are always set to default value if not initialised.
	public void go(){
		x++;  // no error as referencing instance variable in instance method
	}
	
	public static void main(String args[]){
/*		x++;  // error cz referencing instance variable directly in static block
 *    go(); 
*/	
	Test1 test1= new Test1();  
	test1.x+=1;   // now we can refer
	
	}
}
