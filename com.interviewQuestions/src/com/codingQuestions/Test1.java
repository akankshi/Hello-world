package com.codingQuestions;

import java.util.ArrayList;
import java.util.List;


//to print duplicate characters from a String
public class Test1 {
	//way1
/*	private static void search(String str1, int start,int end,List<String> finalList){
		

		if(start<end){			
			
			while(start<end){
				int index=start;
				for(int j=index+1;j<=end;j++){			
					if(String.valueOf(str1.charAt(j)).equalsIgnoreCase(String.valueOf(str1.charAt(index)))){
						if(!(finalList.contains(String.valueOf(str1.charAt(j))))){
							finalList.add(String.valueOf(str1.charAt(j)));
						}
					}
				
				}
				start++;
			}			
			
			
		}else{
			
			System.out.println("No more than one element in the string--> NO DUPLICATES");
		}
		
		
	}

	public static void main(String args[]){
		List<String> finalList= new ArrayList<String>();
		String str1="bangalorer";	
			search(str1,0,str1.length()-1,finalList);	
			
			if(!finalList.isEmpty() && finalList.size()>0){
				for(String s: finalList){
					System.out.print(" "+ s);
				}
			}

	}*/
	
	public static void main(String[] args){
		
		String str2="bangalorer";
		List<String> finalList2= new ArrayList<String>();
		
		for(int i=0;i<str2.length();i++){
			char c=str2.charAt(i);
			if(str2.indexOf(c)!=str2.lastIndexOf(c) && !finalList2.contains(String.valueOf(c))){				
				finalList2.add(String.valueOf(c));
			}	
			
		}
		
		if(!finalList2.isEmpty() && finalList2.size()>0){
			for(String s: finalList2){
				System.out.print(" "+ s);
			}
		}
	}
}
