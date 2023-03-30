package com.concurrency.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection connection = null;
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist?allowPublicKeyRetrieval=true&useSSL=false", "root", "adminroot");
		
		return connection;
	}

}
