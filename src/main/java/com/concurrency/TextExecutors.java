package com.concurrency;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.concurrency.dao.UserDao;
import com.concurrency.runnable.UserProcessor;

public class TextExecutors {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService service = Executors.newSingleThreadExecutor();
		List<String> users = gerUserInformation();
		
		for (String user : users) {
			UserDao dao = new UserDao();
			Future<Integer> f = service.submit(new UserProcessor(user, dao));
			System.out.println("Result of the operation is: " + f.get());
		}
		
		service.shutdown();
		System.out.println("Main execution over!");

	}
	
	public static List<String> gerUserInformation(){
		List<String> users = new ArrayList<>();
		try (
				FileReader fr = new FileReader("C:\\Users\\USER\\THDtraining\\concurrencyTraining\\training\\src\\main\\resources\\sample.txt");
				BufferedReader br = new BufferedReader(fr);
				){
			
			String line;
			
			while((line = br.readLine()) != null) {
				System.out.println(Thread.currentThread().getName() + " is reading line " + line);
				users.add(line);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return users;
	}
}
