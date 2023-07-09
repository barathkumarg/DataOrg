package com.DataOrg.DAO;
import com.DataOrg.logger.FileLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.DataOrg.Bean.User;
import com.DataOrg.JDBCconn.MySQLConnectionManager;

public class InsertUser {
	
	FileLogger fileLogger = new FileLogger(); 

	public boolean insertuser(User userbean) {
		
		if (checkuser(userbean))
			return false; //user exists unable to insert again 
		
		//user insert login
		MySQLConnectionManager mysql = MySQLConnectionManager.getInstance();
		
		try (Connection connection = mysql.getconnection();
	             PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username,email, password) VALUES (?, ?, ?)")){
			//set parameters
			statement.setString(1, userbean.getUsername());
			statement.setString(2, userbean.getEmail());
			statement.setString(3, userbean.getPassword());

			
			//execute the query to check user already exists
			int rowsAffected = statement.executeUpdate();
			fileLogger.logInfo("insert operation completed");
            return rowsAffected > 0;
			
		}catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	} 
	
	
	public static boolean checkuser(User userbean) {
		
		
		FileLogger logger = new FileLogger();
		MySQLConnectionManager mysql = MySQLConnectionManager.getInstance();

		try (Connection connection = mysql.getconnection();
	             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?")){
			//set parameters
			statement.setString(1, userbean.getEmail());
			
			//execute the query to check user already exists
			try (ResultSet resultSet = statement.executeQuery()) {
           	logger.logInfo("user check for insertion completed");
               return resultSet.next(); // true if a matching user is found
           }
	
		}catch (SQLException e) {
           e.printStackTrace();
       }
		return false;
	}

}
