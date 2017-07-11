package com.threadConcepts;

public class Test6 {

	public static void main(String argd[]){
		
	Thread t= new Thread();
	try {
		t.wait();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	t.notify();
		
		
	}
}
