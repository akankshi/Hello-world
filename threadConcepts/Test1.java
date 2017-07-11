package com.threadConcepts;

class MyRunnable implements Runnable{

	@Override
	public void run() {
		
		System.out.println("thread running");	
		try {
			throw new Exception();
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
	}
	
}
public class Test1 {

	public static void main(String args[]){
		
		System.out.println("entered main");
		try{
			doStuff();
		}catch(Exception e ){
			System.out.println("catch block");
		}
		
		
		System.out.println("after doStuff in main");
		MyRunnable myRunnable= new MyRunnable();
		
		Thread t= new Thread(myRunnable);
		//t.start(); // new stack of thread
		
		t.run(); // no now thread stack cz just a method call
		
	}
	
	static void doStuff() throws Exception{
		System.out.println("entered doStuff");
		
			throw new Exception();
		
	}
}
