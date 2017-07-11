package com.interviewQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Collection1 {

	public static void main(String[] args){
		
		//func1 use
		List<Integer> list1= new ArrayList<Integer>();
		list1.add(1);list1.add(2);list1.add(3);
		
		  System.out.println(list1);
	
		
		//func2 use
		List<Integer> list2= new ArrayList<Integer>();
		list2.add(4);list2.add(5);list2.add(6);		
		list1.addAll(list2);		
		for(int i : list1){			
			System.out.print(" "+ i);			
		}
		System.out.println();
		//use3
			list1.add(2, 8);
			for(int i : list1){				
				System.out.print(" "+ i);				
			}
			
			//use4
			Collections.sort(list1);			
			System.out.println();		
			for(int i : list1){				
				System.out.print(" "+ i);				
			}
			//use5			
			list1.remove(3);
			System.out.println();		
			for(int i : list1){				
				System.out.print(" "+ i);				
			}
			
			//use6
			List<Integer> list3= new ArrayList<Integer>();
			list3.add(1);list3.add(5);list3.add(3);
			list1.removeAll(list3);
			System.out.println();		
			for(int i : list1){				
				System.out.print(" "+ i);				
			}
			
			//use7
			List<Integer> list4= new ArrayList<Integer>();
			list4.add(6);	list4.add(8);	
			list4.add(9);
			list1.retainAll(list4);
			System.out.println();		
			for(int i : list1){				
				System.out.print(" "+ i);				
			}
			System.out.println();
			
			//use8
			list1.clear();
			System.out.println();
			System.out.println(list1.size());		
			for(int i : list1){				
				System.out.print(" "+ i);				
			}
			
			System.out.println(list1.isEmpty());
			
			//use9
			System.out.println(list1.contains(8));
			
			//use10
			int a=8;
			List<Integer> list5= new  ArrayList<Integer>();
				
			
		Object[] arr= list1.toArray();
		System.out.println();
		Iterator itr= list5.iterator();
		while(itr.hasNext()){
			System.out.print(itr.next());		
		}
		
		
	}
}
