package com.flowControlConcepts;

import java.io.File;
import java.io.FileOutputStream;

public class Test2 {

	public static void main(String args[]){
		
		int age=14;
		outer:
			while(age<=21){
				age++;
				if(age==16){
					System.out.println("get driver license");
					continue outer;
				}else{
					System.out.println("another year");
				}
			}
		
	/*	outer2:
			while(age<=21){
				age++;
				if(age==16){
					System.out.println("get driver license");
					continue outer;
				}else{
					System.out.println("another year");
				}
			}*/
		
		try{
			File fileInput=new File("trigger.properties");
		}catch(Exception e){
			
		}
	}
}
