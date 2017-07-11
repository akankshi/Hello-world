package com.sortAlgos;

import java.util.Arrays;

public class SelectionSort1 {

	
	public static void main(String[] args){

		int arr1[]={44,11,66,33,22,77,43,55,9,79};
		int arr2[]=Arrays.copyOf(arr1, arr1.length);
		
		System.out.println("before sorting : ");				
		printArrayElemnts(arr1);
		
		//asc order sort
		for(int i=0;i<arr1.length-1;i++){
			
			int index=i;
			for(int j=i+1;j<arr1.length;j++){
				
				if(arr1[j]<arr1[index]){				
					index=j;
				}
			}
		
				int tempSwap=arr1[index];
				arr1[index]=arr1[i];
				arr1[i]=tempSwap;				
			
		}
		
		
		System.out.println("after sorting in  ascending order : ");					
		printArrayElemnts(arr1);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		System.out.println("before sorting : ");				
		printArrayElemnts(arr2);
		
	//sort desc order
		for(int i=0;i<arr2.length-1;i++){
			
			int index=i;
			for(int j=i+1;j<arr2.length;j++){
				
				if(arr2[j]>arr2[index]){				
					index=j;
				}
			}
		
				int tempSwap=arr2[index];
				arr2[index]=arr2[i];
				arr2[i]=tempSwap;				
			
		}
		
		
		System.out.println("after sorting in  descending order : ");					
		printArrayElemnts(arr2);		


	}
	private static void printArrayElemnts(int[] arr1) {
		for(int i:arr1){
			System.out.print(" " + i);
		}
		System.out.println("------------------");
	}


	
}
