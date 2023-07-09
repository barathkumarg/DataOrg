package com.DataOrg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.DataOrg.Bean.UserTableMeta;
import com.DataOrg.JDBCconn.MySQLConnectionManager;
import com.DataOrg.logger.FileLogger;

public class InsertTableMeta {

	public static boolean insertUserMeta(UserTableMeta bean) {
		FileLogger logger = new FileLogger();
		MySQLConnectionManager mysql = MySQLConnectionManager.getInstance();
		
		try (Connection connection = mysql.getconnection();
	             PreparedStatement statement = connection.prepareStatement("INSERT INTO UserTable (UserEmail,UserTableName, DbName) VALUES (?, ?, ?)")){
			//set parameters
			statement.setString(1, bean.getUserEmail());
			statement.setString(2, bean.getUserTableName());
			statement.setString(3, bean.getDbName());

			
			//execute the query to check user already exists
			int rowsAffected = statement.executeUpdate();
			logger.logInfo("insert operation completed");
            return rowsAffected > 0;
			
		}catch (SQLException e) {
            e.printStackTrace();
        }
		
		return false;
		
	}
	
	
	
}
