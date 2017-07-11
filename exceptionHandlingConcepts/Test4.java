package com.exceptionHandlingConcepts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Test4 {

	public static void main(String args[]){
		
		try {
			FileOutputStream abc= new FileOutputStream("aa.txt");
		} 
		 catch (Exception e) {
				
				e.printStackTrace();
			}
	/*	catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}*/
		
	}
}
