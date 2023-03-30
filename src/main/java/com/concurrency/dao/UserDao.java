package com.concurrency.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.concurrency.bean.UserEntity;

public class UserDao {
	
	public int saveUser(UserEntity user) throws ClassNotFoundException, SQLException {
		
		int rows = 0;
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement("insert into user_table values(?,?,?,?,?,?)");
		statement.setInt(1, user.getIduser());
		statement.setString(2, user.getEmail());
		statement.setString(3, user.getFirst_name());
		statement.setString(4, user.getLast_name());
		statement.setString(5, user.getUser_password());
		statement.setString(6, user.getUser_role());
		
		rows = statement.executeUpdate();
		
		return rows;
		
	}
}
