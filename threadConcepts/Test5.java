package com.threadConcepts;

public class Test5 implements Runnable{

	StringBuffer str= new StringBuffer();
	char letter='A';
	
	
	@Override
	public void run() {

		synchronized (this) {
			
			for(int i=1;i<=100;i++){				
				str.append(letter);
				System.out.print(letter);
			}			
			letter+=1;
		}	
		
	}
	
	public static void main(String args[]){
		Test5 test5 = new Test5();
		
		Thread t1= new Thread(test5);		
		Thread t2= new Thread(test5);		
		Thread t3= new Thread(test5);	
		
		t1.start();
		t2.start();
		t3.start();
	}

}
