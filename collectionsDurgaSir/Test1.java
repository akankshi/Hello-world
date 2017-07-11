package com.collectionsDurgaSir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;


public class Test1 {

	public static void main(String args[]){
		
		int[] arr1={1,2,4,4,8};
	
		List arr2= new ArrayList<String>();
		List arr3= new Stack();
		System.out.println(arr2.size());
		arr2.add(22);
		arr2.add(33);
		
		arr2.add(33);
		
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
		
		
		for(Object obj1:arr2){
			System.out.println(obj1);
		}
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
		
		arr2.remove(1);
		for(Object obj1:arr2){
			System.out.println(obj1);
		}
		System.out.println(arr2.size());

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>After sort");
		Collections.sort(arr2);
		
		for(Object obj1:arr2){
			System.out.println(obj1);
		}
	
	}
}
