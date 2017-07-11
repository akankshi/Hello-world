package com.castingConcepts;

class Tree{}

public class RedWood extends Tree {
	
	public static void main(String args[]){
		new RedWood().go();
	}
	
	void go(){
		System.out.println("1");
		go2(new Tree(),new RedWood());
		System.out.println("2");
		go2(((RedWood)new Tree()),new RedWood());
		System.out.println("3");
	}
	
	void go2(Tree t1,RedWood r1){
		System.out.println("4");
		RedWood r2=(RedWood)t1;
		System.out.println("5");
		Tree t2=(Tree)r1;
		System.out.println("6");
	}

}
