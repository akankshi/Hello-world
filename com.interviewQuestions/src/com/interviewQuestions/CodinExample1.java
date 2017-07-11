package com.interviewQuestions;

import java.util.Arrays;

public class CodinExample1 {

	public static void main(String args[]){
		
		//1 question: to print highest element in array
		int[]  arr1= {67,33,121,1,100,8,99};
		int highest=0;
		//way1
		if(null!=arr1 && arr1.length>0){
			
			for(int i:arr1){
				if(i>highest){
					highest=i;
				}
			}
			
			System.out.println("the highest number is "+ highest);
		}
		
		//way2
		Arrays.sort(arr1);
		System.out.println("the highest number is "+ arr1[arr1.length-1]);
		
		//2 question: to print 2 highest element in the array
		
		//way1
		System.out.println("the second highest number is "+ arr1[arr1.length-2]);
		
		//way2
		int secondHighest=0;
		if(null!=arr1 && arr1.length>0){
			
			for(int i:arr1){
				if(i!=highest && i>secondHighest){
					secondHighest=i;					
				}
			}
			
			System.out.println("the second highest number is "+ secondHighest);
		}
		
		
		
	}
}
