package com.flowControlConcepts;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {
	
	public static void main(String args[]){
		
		/*int i=0;
		for(;i<10;){
			
			System.out.println("ak");
			i++;
		}*/
		
		/*for(;;){}*/
		int abc=3;
		
		abc:   //variable and label can have same names
		for(int i=0,j=0;(i<10) && (j<10);i++,j++){
			System.out.println("i is "+i+" j is " +j);
		}
	}
	


}
