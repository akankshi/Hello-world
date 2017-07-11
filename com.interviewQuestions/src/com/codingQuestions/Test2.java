package com.codingQuestions;

import java.util.Arrays;

//to check 2 strings are anagram or not
public class Test2 {

	public static void main(String[] args){
		
		String str1="silent";
		String str2="listen";
		
		int res=checkAnagram(str1, str2);	
		if(res==-1){
			System.out.println("NO");
		}else if(res==0){
			System.out.println("YES");
		}
		
		
		
		
	}

	private static int checkAnagram(String str1, String str2) {
		char[] str1Ar= new char[str1.length()];
		char[] str2Ar= new char[str2.length()];
		if(str1.length()==str2.length()){
			
			for(int i=0;i<str1.length();i++){
				str1Ar[i]=str1.charAt(i);
			}
			
			for(int i=0;i<str2.length();i++){
				str2Ar[i]=str2.charAt(i);
			}
			Arrays.sort(str1Ar);
			Arrays.sort(str2Ar);
			
			for(int i=0;i<str2.length();i++){
				if(str1Ar[i]!=str2Ar[i]){
					return -1;
				}
			}
			
			return 0;
		}else{
			return -1;
		}
		
	}
}
