package com.operatorConcepts;

class A{}
interface D{}

class B{}
class C extends B implements D{}
class E extends C{}

public class Test1 {

	public static void main(String args[]){
		
		if(5.0==5){
			System.out.println("yes");
		}
		

		if(5.1==5) {
			System.out.println("no");
		}
		
		String a="akanksha";
		int b=1;
		int c=2;
		System.out.println(a+b+c);
		System.out.println(a+(b+c));
		System.out.println(b+c+a);
		System.out.println((b+c)+a);
		
		System.out.println(b++ + c+ b);
		
		C objc=new C();
		
		System.out.println(new E() instanceof C);
	/*	System.out.println(new E() instanceof A);   //Complie error as no hierarcy */
		
	}
}
