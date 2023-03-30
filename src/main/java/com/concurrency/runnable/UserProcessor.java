package com.concurrency.runnable;

import java.util.StringTokenizer;
import java.util.concurrent.Callable;

import com.concurrency.bean.UserEntity;
import com.concurrency.dao.UserDao;

public class UserProcessor implements Callable<Integer>{
	
	private String userRecord;
	private UserDao dao;
	
	public UserProcessor(String userRecord, UserDao dao) {
		this.userRecord = userRecord;
		this.dao = dao;
	}

	@Override
	public Integer call() throws Exception {
		
		int rows;
		
		System.out.println(Thread.currentThread().getName() + " processing record of " + userRecord);
		
		StringTokenizer tokenizer = new StringTokenizer(userRecord, ",");
		UserEntity user = null;
		
		while(tokenizer.hasMoreTokens()) {
			user = new UserEntity();
			user.setIduser(Integer.valueOf(tokenizer.nextToken()));
			user.setEmail(tokenizer.nextToken());
			user.setFirst_name(tokenizer.nextToken());
			user.setLast_name(tokenizer.nextToken());
			user.setUser_password(tokenizer.nextToken());
			user.setUser_role(tokenizer.nextToken());
		}
		
		rows = dao.saveUser(user);
		return rows;
	}

}
