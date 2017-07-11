package com.sortAlgos;

public class HeapSort1 {
	
	private static  void sort(int[] arr1){
		
		int size=arr1.length;
		
		for(int i=size/2-1;i>=0;i--){
			heapify(arr1,size,i);
		}
		
		for(int i=size-1;i>=0;i--){
			int temp=arr1[0];
			arr1[0]=arr1[i];
			arr1[i]=temp;
			
			heapify(arr1, i, 0);
		}
		
	}
	
	private static void heapify(int[] arr1,int size,int i){
		
		int largest=i;
		int left=2*i+1;
		int right=2*i+2;
		
		if(left<size && arr1[largest]<arr1[left]){
			largest=left;
		}
		
		if(right<size && arr1[right]>arr1[largest]){
			largest=right;
		}
		
		if(largest!=i){
			int temp=arr1[largest];
			arr1[largest]=arr1[i];
			arr1[i]=temp;
			
			heapify(arr1, size, largest);
		}
	}

	public static void main(String[] args){
		
	    int arr1[] = {12, 11, 13, 5, 6, 7,14};       
	    
	    sort(arr1);
	    
	    System.out.print("Sorted array in ascending order is : ");
	    printArray(arr1,"ASC");
	    
	    System.out.print("Sorted array in descing order is : ");
	    printArray(arr1,"DESC");
	    
	    
	}

	private static void printArray(int[] arr1,String order) {
		System.out.println();
		if(order.equals("ASC")){
			for(int i=0;i<arr1.length;i++){
		    	System.out.print(" "+ arr1[i]);
		    }
		}
		
		else if(order.equals("DESC")){
			for(int i=arr1.length-1;i>=0;i--){
		    	System.out.print(" "+ arr1[i]);
		    }
		}
		System.out.println();
	    
	}


}
