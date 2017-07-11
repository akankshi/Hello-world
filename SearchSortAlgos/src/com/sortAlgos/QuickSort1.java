package com.sortAlgos;

public class QuickSort1 {
	
	private static int partition(int[] arr1,int low,int high){
		
		int pivot=high;
		int index=low;
		
		for(int j=low;j<=high;j++){
			
			if(arr1[j]<=arr1[pivot]){
				
				int temp=arr1[j];
				arr1[j]=arr1[index];
				arr1[index]=temp;
				
				index++;
			}
		}
		
		return index-1;
	}
	
	private static void sort(int[] arr1,int low,int high){
		
		if(low<high){
			
			int pi=partition(arr1,low,high);
			
			sort(arr1,low,pi-1);
			sort(arr1,pi+1,high);
		}
		
	}
	
	private static void printArray(int[] arr1){		
			System.out.println();
			for(int i=0;i<arr1.length;i++){
				System.out.print(" "+ arr1[i]);
			}
			System.out.println();
		
	}

	public static void main(String[] args){
		
	     int arr1[] = {10, 7, 8, 9, 1, 5};
	     
	     sort(arr1,0,arr1.length-1);
	     
	     System.out.println("sorted array");
	     printArray(arr1);
		
	}
}
