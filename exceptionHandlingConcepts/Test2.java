package com.exceptionHandlingConcepts;

public class Test2 {

	
	public static void main(String args[]){		
		
		Test2 test2=new Test2();
		try {
			String revStr=test2.reverse("");
			System.out.println("reverse string "+ revStr);
		} catch (Exception e) {			
			System.out.println("Exception occired");
		}
		finally{
			System.out.println("finally block occired");
		}
	}
	
	public String reverse(String in) throws Exception{
		String str="";
		
		if(in.length()==0){
			throw new Exception();
		}
		for(int i=in.length()-1;i>=0;i--){
			str=str+in.charAt(i);
		}
		
		return str;
	}
}
