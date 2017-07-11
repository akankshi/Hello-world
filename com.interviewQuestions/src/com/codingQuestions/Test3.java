package com.codingQuestions;
//to print number of words in a  string
public class Test3 {

	public static void main(String[] args){
		
		String str1="My Fav city is blore";
		String[] strArr=str1.split(" ");
		int ctr=0;
		for(String s: strArr){
			if(s!="\t"){
				ctr++;
			}
		}
		System.out.println("No of words " + ctr);
		
	}
}
