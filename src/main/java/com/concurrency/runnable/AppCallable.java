package com.concurrency.runnable;

import java.util.concurrent.Callable;

public class AppCallable implements Callable<Integer>{
	
	int numUpTo;
	
	public AppCallable(int numUpTo) {
		this.numUpTo = numUpTo;
	}
	
	@Override
	public Integer call() throws Exception {
		int result = 0; 
		System.out.println(Thread.currentThread().getName() + " is calculating the sum of all the numbers up to " + this.numUpTo);
		for(int i = 1; i <= this.numUpTo; ++i) {
			Thread.sleep(1000);
			result+=i;
		}
		
		return result;
	}
}
