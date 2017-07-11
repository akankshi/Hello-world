package com.overloadingOverridding;


class Person{
	String name="person";
	
	void go(){
		System.out.println("Person go");
	}
}

class Female extends Person{
	String name="female";
	
	void go(){
		System.out.println("Female go");
	}
}
public class Test6 {

	public static void main(String args[]){
		
		Person pp=new Person();
		System.out.println(pp.name);
		pp.go();
		
		System.out.println(">>>>>>>>>>>>>>>");
		
		Female f=new Female();
		System.out.println(f.name);
		f.go();
		System.out.println(">>>>>>>>>>>>>>>");
		Person pf=new Female();
		System.out.println(pf.name);
		pf.go();
		
		
	}
}
