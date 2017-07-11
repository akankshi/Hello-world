package com.primitiveTypeConcepts;

public class Test2 {

	public static void main(String args[]){
		
		byte a=4;
		byte b=5;
		
		
		float f=334.435f;
		short e=(short)f;
		System.out.println(e);
		
		//comparison
		byte c=(byte)(a+b);
		System.out.println(c);		
		/*byte c=a+b;*/   //any expression happens then return type becomes int not stays byte aand even if small value cant take
		
		byte h= 5;
		h+=7;  // compund operator implicat casting takes..

		
		/*//comparisons
		float g=1.22;	//though value is small but cant take double value in float without cast
		byte h=34;      // vale is small and can take int into byte withut cast
		
		//comparisons
		byte i=3777877; //value is bog so cant take the value now without cast
		byte j=(byte)3777877;  //can take the value if casted but will ignore extra bits
		*/
		//comparisons
		float k=(float)435656787965432.546;
		System.out.println(k);
		
	}
}
