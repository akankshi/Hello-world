package com.overloadingOverridding;


/*
 * first program
 class Star{
	
	public void go(){}
}
public class Test5 extends Star {

	public String go(){return null;}
}


*/

//2nd program
class Star{
	
	public Star go(){ //focus on return type
		
		System.out.println("Star");
		return new Star();
	}
}

public class Test5 extends Star{
	
/*	public Test5 go(){
		
		System.out.println("Test5");
		return new Test5();
	}
	*/
	public static void main(String args[]){
		
		Star obj=new Test5();
		obj.go();
	}
}