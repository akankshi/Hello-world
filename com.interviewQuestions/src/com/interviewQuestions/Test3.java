package com.interviewQuestions;

import java.io.IOException;

 class Test3Parent {
	
	Test3Parent fun() throws Exception{
		System.out.println("parent");
		return new Test3Parent();
	}

}

class Test3 extends Test3Parent{
	 protected Test3 fun() throws IOException,ArrayIndexOutOfBoundsException{
		System.out.println("child");
		return new Test3();
	}
	public static void main(String args[]){
		
		Test3Parent tt= new Test3();
		try {
			tt.fun();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
}
