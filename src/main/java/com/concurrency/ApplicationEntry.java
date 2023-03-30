package com.concurrency;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.concurrency.runnable.AppCallable;
import com.concurrency.runnable.AppThread;

public class ApplicationEntry {

	public static void main(String[] args) {
		System.out.println("Hello world");
		
		Runnable r1 = () -> {
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
		};
		
		Thread t = new Thread(r1);
		t.setName("Ruunable thread - 0");
		AppThread ap1 = new AppThread();
		AppThread ap2 = new AppThread();
		AppThread ap3 = new AppThread();
		List<AppCallable> l = new ArrayList<>();
		l.add(new AppCallable(8));
		l.add(new AppCallable(5));
		l.add(new AppCallable(6));
		l.add(new AppCallable(3));
		l.add(new AppCallable(9));
		
		ExecutorService service = Executors.newFixedThreadPool(3);
		
		for (AppCallable appCallable : l) {
			Future<Integer> future = service.submit(appCallable);
			try {
				Integer integer = future.get();
				System.out.println("Sum is " + integer);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			} 
		}
		
		service.shutdown();
		
		t.start();
		ap1.start();
		ap2.start();
		ap3.start();

	}

}
