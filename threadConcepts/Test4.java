package com.threadConcepts;

import javax.sql.rowset.spi.SyncResolver;

class Account{
	private int balance=100;
	
	public int getBalance() {
		return balance;
	}
	
	public void withdraw(int amt){
		balance-=amt;
	}
	
  
	
	public void func1(){

		System.out.println("Non Syn method called  by :" + Thread.currentThread().getName());
	}

	public  synchronized void makeWithdrawal(int amt){
		//balance=balance-amt;
		if(getBalance()>=amt){
			System.out.println("withdraw request approved for :" + Thread.currentThread().getName());
			withdraw(amt);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		
			System.out.println("withdrawal done by :" + Thread.currentThread().getName());
		}
		
		
	}	
	
	
	
}
class Demo2 implements Runnable{

	private Account account=new Account();
	@Override
	public void run() {
		for(int i=1;i<=2;i++){
			account.func1();
			account.makeWithdrawal(10);
		}

		
	}
	
}


public class Test4 {

	public static void main(String args[]){
		
		Demo2 demo2= new Demo2();
		
		Thread t1= new Thread(demo2);
		t1.setName("Akanksha");
		t1.start();
		
		Thread t2= new Thread(demo2);
		t2.setName("Aayush");
		
		t2.start();
		
	}
}
