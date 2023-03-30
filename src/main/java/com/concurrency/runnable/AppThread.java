package com.concurrency.runnable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class AppThread extends Thread{
	@Override
	public void run() {
		
		int count = 0;
		try (
				FileReader fr = new FileReader("C:\\Users\\USER\\THDtraining\\concurrencyTraining\\training\\src\\main\\resources\\sample.txt");
				BufferedReader br = new BufferedReader(fr);
				){
			
			String line;
			
			while((line = br.readLine()) != null) {
				System.out.println(Thread.currentThread().getName());
				StringTokenizer st = new StringTokenizer(line);
				while(st.hasMoreTokens()) {
					System.out.println(st.nextToken());
					count++;
				}
			}
			System.out.println("Number of words in the file are: " + count);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
