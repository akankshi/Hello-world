package com.dataStructre;


class Node{

	private int data;
	private Node nextNode;
	
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public Node getNextNode() {
		return nextNode;
	}
	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
	
}
public class NormalLinkedList {
	
	private int size=0;
	private Node root=null;
	
	private Node temp=null;
	
	//adding at the end of the list
	void add(int x){
		Node newNode=new Node();
		newNode.setData(x);
		newNode.setNextNode(null);
		if(size==0){
			root=newNode;			
		}else {
			temp.setNextNode(newNode);					
		}	
		temp=newNode;
		size++;
	}
	
	//remove nth elment from linked list..n starts from 1 
	void remove(int n){
		
		
	}
	
	void printList(){
		
		System.out.println("LIST : ");
		if(size>0){
			Node navNode=root;
			while(navNode.getNextNode()!=null){
				System.out.print(" "+ navNode.getData());
				navNode=navNode.getNextNode();
			}
			System.out.print(" "+ navNode.getData());
		}
	}
	
	public static void main(String args[]){
		
		NormalLinkedList linkedList= new NormalLinkedList();
		linkedList.add(10);
		linkedList.add(20);
		linkedList.add(30);
		linkedList.add(40);
		
		
		linkedList.remove(3);
		linkedList.printList();
		
		
	}

}
