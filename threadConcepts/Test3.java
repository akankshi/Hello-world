package com.threadConcepts;

class Demo1 implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " started");
		for(int i=1;i<=100;i++){
			
			try {
				Thread.sleep(1000);
				if(i%10==0){
					System.out.println("The value of i is :" + i);
				}
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
	}	
}

public class Test3 {

	public static void main(String args[]){
		
		Demo1 demo1= new Demo1();
		Thread t= new Thread(demo1);
	/*	t.setPriority(Thread.MAX_PRIORITY);*/
		t.setName("my first thread");
		t.start();
		
		
	}
}
