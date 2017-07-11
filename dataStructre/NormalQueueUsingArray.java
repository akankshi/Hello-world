package com.dataStructre;

public class NormalQueueUsingArray {
	
	int marks[]= new int[10];
	
	int front=-1;
	int rear=-1;

	boolean isEmpty(){
		if(front==-1 && rear==-1){
			return true;
		}
		
		return false;
	}
	
	boolean isFull(){		
		if(rear==(marks.length-1)){
			return true;
		}		
		return false;		
	}
	
	
	void enQueue(int x){

		if(!isFull()){
			if(isEmpty()){
				front=0;
				rear=0;			
			}
			else{
				rear+=1;
			}
			marks[rear]=x;
		}
		else {
			System.out.println("Overflow issue!!!");
		}
		
		this.printQueue();
	}
	
	void deQueue(){
		
		if(isEmpty()){
			System.out.println("No Elements to remove!!!");
		}
		else{
			if(front==rear){
				front=-1;
				rear=-1;
			}
			else{
				front++;
			}
		}
		
		this.printQueue();
	}
	
	void printQueue(){
		System.out.print("QUEUE : ");
		if(front==rear && rear==-1){
			System.out.println(" No elments in queue");
		}
		else{
			

			for(int i=front;i<=rear;i++){
				System.out.print(" " + marks[i]);
			}
			
			System.out.println();
		
		}
		
	}
	public static void main(String args[]){
		
		NormalQueueUsingArray queue = new NormalQueueUsingArray();
		
	
		queue.enQueue(22);
		queue.enQueue(33);
		queue.enQueue(55);
		queue.enQueue(66);
		queue.enQueue(11);
		
		queue.deQueue();
		queue.deQueue();
		queue.deQueue();
		queue.deQueue();queue.deQueue();
		queue.deQueue();
		queue.deQueue();
		
		
	
		
		
		
		
		
		
		
	}
}
