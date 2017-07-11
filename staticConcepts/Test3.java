package com.staticConcepts;


class Star{
	static void go(){
		System.out.println("a");
	}	
}


public class Test3 extends Star{
	
	static void go(){
		System.out.println("b");
	}
	
	void run(){};
	
	public static void main(String args[]){
		
		Star.go();
		Test3.go();
		System.out.println("--------------------------------------------");
		Star[] stars={new Star(),new Test3(),new Star()};
		for(Star star:stars){
			star.go();
			
			if(star instanceof Test3){
				Test3 obj=(Test3)star;
				obj.go();
			}
			
			/*((Test3)star).run();*/
			System.out.println(">>>>>>>>>>>>>>");
		}
	}

}
