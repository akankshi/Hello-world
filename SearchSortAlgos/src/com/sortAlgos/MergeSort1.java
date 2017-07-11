package com.sortAlgos;

public class MergeSort1 {

	private static void merge(int arr1[],int l,int m,int r){
		
		int n1=m-l+1;
		int n2=r-m;
		
		int[] L=new int[n1];
		int[] R=new int[n2];
		
		for(int i=0;i<n1;i++){
			L[i]=arr1[l+i];
		}
		
		for(int j=0;j<n2;j++){
			R[j]=arr1[m+1+j];
		}
		
		int i=0,j=0,k=l;
		while(i<n1 && j<n2){
			
			if(L[i]>=R[j]){
				arr1[k]=R[j];
				j++;
			}
			else{
				arr1[k]=L[i];
				i++;
				
			}
			k++;
		}
		
		while(i<n1){
			arr1[k]=L[i];
			i++;
			k++;
		}
		
		while(j<n2){
			arr1[k]=R[j];
			j++;
			k++;
		}
		
	}
	private static void sort(int[] arr1,int l,int r){
		
		if(l>=0 && l<r){
			int m=(l+r)/2;
			
			sort(arr1,l,m);
			sort(arr1, m+1, r);
			
			merge(arr1,l,m,r);
		}
		
	}
	public static void main(String[] args){
		
		int[] arr1={12,11,13,5,6,7};
		
		System.out.print("Given Array ");
		printArray(arr1);
		
		sort(arr1,0,arr1.length-1);
		
		System.out.print("Sorted Array ");
		printArray(arr1);
		
		
	}

	private static void printArray(int[] arr1) {
		System.out.println();
		for(int i=0;i<arr1.length;i++){
			System.out.print(" "+ arr1[i]);
		}
		System.out.println();
	}
}
