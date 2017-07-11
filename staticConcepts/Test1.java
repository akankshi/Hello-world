package com.staticConcepts;

//program1
public class Test1 {
	
	static int instanceCount=0;
	Test1(){
		instanceCount+=1;
	}
	
	public static void main(String args[]){
		
		Test1 obj1=new Test1();
		new Test1();
		Test1 obj3=new Test1();
		
		System.out.println("NUMBER OF INSTANCES :" + instanceCount);
		
	}

}

//program2
/*public class Test1 {
	
	 int instanceCount=0;
	Test1(){
		instanceCount+=1;
	}
	
	public static void main(String args[]){
		
		Test1 obj1=new Test1();
		new Test1();
		Test1 obj3=new Test1();
		
		System.out.println("NUMBER OF INSTANCES :" + instanceCount);
		
	}

}*/
