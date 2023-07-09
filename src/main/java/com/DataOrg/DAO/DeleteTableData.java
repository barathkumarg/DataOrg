package com.DataOrg.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.DataOrg.JDBCconn.MySQLConnectionManager;
import com.DataOrg.logger.FileLogger;

public class DeleteTableData {
	
	//Delete action to delete the particular data
	public static boolean DeleteData(String DbName,String tableName,String primaryColName,String primaryColValue) {
		FileLogger logger = new FileLogger();
		MySQLConnectionManager mysql = MySQLConnectionManager.getInstance();


		//insert the table for the desired database
		try (Connection connection = mysql.getEmptyconnection();
	             Statement statement = connection.createStatement();){
			
			
			//update the db
			String useDbQuery = "USE " + DbName;
            statement.executeUpdate(useDbQuery);
            
            //delete Action
            // Construct the delete query
            String deleteQuery = "DELETE FROM " + tableName + " WHERE " + primaryColName + " = '" + primaryColValue + "'";

            // Execute the delete query
            int rowsAffected = statement.executeUpdate(deleteQuery);

            // Check if the deletion was successful
            return rowsAffected > 0;
		
		
	} catch (SQLException e) {
        logger.logInfo("An error occurred while checking if the database exists: " + e.getMessage());
        return false;
    }
	}
}
