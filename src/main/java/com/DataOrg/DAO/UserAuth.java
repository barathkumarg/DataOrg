package com.DataOrg.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.DataOrg.JDBCconn.MySQLConnectionManager;
import com.DataOrg.logger.FileLogger;

public class UserAuth {
	FileLogger fileLogger = new FileLogger(); 
	private String email;
	private String password;

	public UserAuth(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public boolean checkuser(){
		
		MySQLConnectionManager mysql = new MySQLConnectionManager();

        try (Connection connection = mysql.getconnection();
	             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?")) {
        	
	            statement.setString(1, email);
	            statement.setString(2, password);

	            try (ResultSet resultSet = statement.executeQuery()) {
	            	fileLogger.logInfo("login user execution completed");
	                return resultSet.next();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle database connection or query errors
	        }
	        	
        

	        return false;
	    }
}