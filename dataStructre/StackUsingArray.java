package com.dataStructre;

public class StackUsingArray {

	int marks[]=new int[10];
	
	int top=-1;
	
	void push(int x){
		top+=1;
		if(top>=0 && top<marks.length){
			marks[top]=x;
		}
		else{
			
			System.out.println("ALERT!!!! STACK OVERFLOW");
		}	
	}
	
	void pop(){
		top-=1;
	}
	
	boolean isEmpty(){
		boolean val=false;
		if(top<=-1){
			return true;
		}
		return val;
	}
	
	
	void printStack(){
		System.out.print("STACK : ");
		for(int i=0;i<=top;i++){
			System.out.print(" "+ marks[i]);
		}
		System.out.println("\n");
	}
	
	public static void main(String args[]){
		
		StackUsingArray stack= new StackUsingArray();
		stack.printStack();
		
		stack.push(33);
		stack.printStack();
		
		stack.push(44);
		stack.printStack();
		
		stack.pop();
		stack.printStack();
		
		stack.pop();
		stack.printStack();
		
		stack.pop();
		stack.printStack();
		
		stack.pop();
		stack.printStack();
		
		System.out.println(stack.isEmpty());
		
	}
}
