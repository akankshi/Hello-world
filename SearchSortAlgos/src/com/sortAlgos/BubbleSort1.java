package com.sortAlgos;

import java.util.Arrays;

public class BubbleSort1 {
	public static void main(String args[]){
		
		int arr1[]={54,33,4,22,12,76,99,20,1};
		int arr2[]=Arrays.copyOf(arr1, arr1.length);
		
		System.out.println("before sorting : ");				
		printArrayElemnts(arr1);

		//sort asc
		sortAscOrder(arr1);
		
		
		System.out.println("after sorting in  ascending order : ");					
		printArrayElemnts(arr1);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		System.out.println("before sorting : ");				
		printArrayElemnts(arr2);
		
		//sort desc
		sortDescOrder(arr2);
		
		
		System.out.println("after sorting in  descending order : ");					
		printArrayElemnts(arr2);	
		
	}

	private static void sortDescOrder(int[] arr1) {
		for(int i=0;i<arr1.length;i++){
			for(int j=1;j<(arr1.length-i);j++){
				if(arr1[j-1]<arr1[j]){
					int temp=arr1[j-1];
					arr1[j-1]=arr1[j];
					arr1[j]=temp;
				}
			}
		}
	}

	private static void sortAscOrder(int[] arr1) {
		for(int i=0;i<arr1.length;i++){
			for(int j=1;j<(arr1.length-i);j++){
				if(arr1[j-1]>arr1[j]){
					int temp=arr1[j-1];
					arr1[j-1]=arr1[j];
					arr1[j]=temp;
				}
			}
		}
	}
	
	private static void printArrayElemnts(int[] arr1) {
		for(int i:arr1){
			System.out.print(" " + i);
		}
		System.out.println("------------------");
	}

}
