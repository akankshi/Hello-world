package com.threadConcepts;

class MyRunnable2 implements Runnable{
	public void run(){
		
		System.out.println("thread running code");
		
	}
}
public class Test2 {
	
	public static void main(String args[]){
		
		MyRunnable2 myRunnable2 = new MyRunnable2();
		Thread t= new Thread(myRunnable2);
	/*	System.out.println(t.getName());
		System.out.println(t.getPriority());
		System.out.println(t.isAlive());
		System.out.println(t.isDaemon());
		System.out.println(t.getState());
		System.out.println(t.currentThread());
		System.out.println(t.getStackTrace());
		System.out.println(t.equals(t.currentThread()));
		System.out.println(t);*/
		
		t.start();
		System.out.println(t.isAlive());
		if(!t.isAlive()){
			System.out.println("hiiiii\\");
			t.start();
		}
		
	
		
	}

}
