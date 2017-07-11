package com.sortAlgos;

import java.util.Arrays;

public class InsertionSort1 {

	public static void main(String[] args) {
		
		int arr1[]={44,11,66,33,22,77,43,55,9,79};
		int arr2[]=Arrays.copyOf(arr1, arr1.length);
		
		System.out.println("before sorting : ");				
		printArrayElemnts(arr1);
		
		//insertion sort algo : ASCENDING ORDER		
		sortAscOrder(arr1);	
		
		System.out.println("after sorting in  ascending order : ");					
		printArrayElemnts(arr1);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		System.out.println("before sorting : ");				
		printArrayElemnts(arr2);
		
		sortDescOrder(arr2);
		
		
		System.out.println("after sorting in  descending order : ");					
		printArrayElemnts(arr2);		

	}
	private static void sortDescOrder(int[] arr2) {
		//insertion sort descending order
		for(int i=1;i<arr2.length;i++){
			int key=arr2[i];
			int j=i-1;
			
			while(j>=0 && arr2[j]<key){
				arr2[j+1]=arr2[j];
				j--;
			}
			
			arr2[j+1]=key;
		}
	}
	private static void sortAscOrder(int[] arr1) {
		for(int i=1;i<arr1.length;i++){
			int key=arr1[i];
			int j=i-1;
			
			while(j>=0 && arr1[j]>key){
				arr1[j+1]=arr1[j];
				j--;
			}
			
			arr1[j+1]=key;
		}
	}
	private static void printArrayElemnts(int[] arr1) {
		for(int i:arr1){
			System.out.print(" " + i);
		}
		System.out.println("------------------");
	}

}
