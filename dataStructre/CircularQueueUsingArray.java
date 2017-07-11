package com.dataStructre;

public class CircularQueueUsingArray {
	
	int marks[]= new int[4];
	
	int front=-1;
	int rear=-1;
	
	boolean isEmpty(){
		if(front==-1 && rear==-1){
			return true;
		}
		
		return false;
	}
	
	boolean isFull(){
		if((rear==(marks.length-1) && front==0) || front==rear+1){
			return true;
		}
		return false;
	}
	
	void enQueue(int x){
		
		if(isEmpty()){
			front=0;
			rear=0;
			marks[rear]=x;
		}else if(isFull()){
			System.out.println("OVERFLOW ISSUE!!!");
			
		}else{
			//elemnts can be added
			
			//if end of array
			if(rear==(marks.length-1)){
				rear=0;		
			}else{
				rear+=1;
				
			}
			marks[rear]=x;
		}
		
		this.printQueue();
	}
	
	void deQueue(){
		if(isEmpty()){
			System.out.println("NO ELMENTS TO REMOVE");
		}else if(front==rear){
			front=-1;
			rear=-1;		
		}else {
			
			//elements can be removed
			if(front==(marks.length-1)){
				front=0;
			}else {
				front++;
			}
			
		}
		
		this.printQueue();
	}
	
	void printQueue(){
		System.out.print(" CIRCULAR QUEUE : ");
		if(front==-1 && rear==-1){
			System.out.println("No elemnts to print");
		}else if(front<=rear){
			for(int i=front;i<=rear;i++){
				System.out.print(" "+ marks[i]);
			}
			System.out.println();
		}else {
			
			for(int i=front;i<marks.length;i++){
				System.out.print(" "+ marks[i]);				
			}
			for(int i=0;i<=rear;i++){
				System.out.print(" "+ marks[i]);				
			}
			System.out.println();
		}
	}
	public static void main(String args[]){
		
		CircularQueueUsingArray circularQueue= new CircularQueueUsingArray();
		
		circularQueue.enQueue(1);
		circularQueue.enQueue(2);
		circularQueue.enQueue(3);
		circularQueue.enQueue(4);
		circularQueue.enQueue(5);
		
		circularQueue.deQueue();
		circularQueue.deQueue();
		
		circularQueue.enQueue(6);
		circularQueue.enQueue(7);
		circularQueue.enQueue(8);
		
		
		
		
	}

}
