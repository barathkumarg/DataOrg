package com.DataOrg.DAO;

import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import com.DataOrg.Bean.UserTable;
import com.DataOrg.JDBCconn.MySQLConnectionManager;

public class InsertTableData {
	
	
	public static boolean insertTable(UserTable bean) {
		
		DbCheck(bean.getDbName());
		
		MySQLConnectionManager mysql = new MySQLConnectionManager();

		//insert the table for the desired database
		try (Connection connection = mysql.getEmptyconnection();
	             Statement statement = connection.createStatement()){
			//logic starts
			
			//update the db
			String useDbQuery = "USE " + bean.getDbName();
            statement.executeUpdate(useDbQuery);
            
            //insert the table
         // Create the SQL CREATE TABLE statement dynamically
            StringBuilder sql = new StringBuilder("CREATE TABLE ");
            sql.append(bean.getTableName()).append(" (");
            
            sql.append(bean.getColumnNames().get(0)).append(" VARCHAR(200) PRIMARY KEY");
            for (int i = 1; i < bean.getColumnNames().size(); i++) {
                if (i > 0) {
                    sql.append(", ");
                }
                sql.append(bean.getColumnNames().get(i)).append(" VARCHAR(200)");
            }
            sql.append(")");
            System.out.println(sql);
            
            // Execute the CREATE TABLE statement
            statement.executeUpdate(sql.toString());
			
			
		} catch (SQLException e) {
            System.out.println("An error occurred while checking if the database exists: " + e.getMessage());
            return false;
        }

		
		return true;
	}
	
	//method checks for the database and if not exists it creats a new one
	public static void DbCheck(String DbName) 
	{
		MySQLConnectionManager mysql = new MySQLConnectionManager();
		
		try (Connection connection = mysql.getEmptyconnection();
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery("SHOW DATABASES")) {

	            while (resultSet.next()) {
	                String existingDbName = resultSet.getString(1);
	                if (existingDbName.equalsIgnoreCase(DbName)) {
	                    return;
	                }
	            }
	            
	            //create the db 
	            String createDbQuery = "CREATE DATABASE " + DbName;
	            statement.executeUpdate(createDbQuery);

	        } catch (SQLException e) {
	            System.out.println("An error occurred while checking if the database exists: " + e.getMessage());
	        }

	        return;
		
		
		
	}
	
	
	//insert the data got from the form in jsp
public static boolean insertData(String DbName,String tableName,List<String>columnNames, List<String> dynamicDataList) {
		

		
		MySQLConnectionManager mysql = new MySQLConnectionManager();

		//insert the table for the desired database
		try (Connection connection = mysql.getEmptyconnection();
	             Statement statement = connection.createStatement();){
			
			
			//update the db
			String useDbQuery = "USE " + DbName;
            statement.executeUpdate(useDbQuery);
            
            //insert Into the table
            StringBuilder insertQuery = new StringBuilder("INSERT INTO ");
            insertQuery.append(tableName).append(" (");

            // Append column names to the query
            for (int i = 0; i < columnNames.size(); i++) {
                insertQuery.append(columnNames.get(i));
                if (i < columnNames.size() - 1) {
                    insertQuery.append(", ");
                }
            }

            insertQuery.append(") VALUES (");

            // Append placeholders for values in the query
            for (int i = 0; i < dynamicDataList.size(); i++) {
                insertQuery.append("?");
                if (i < dynamicDataList.size() - 1) {
                    insertQuery.append(", ");
                }
            }

            insertQuery.append(")");
            //Prepared statement
            PreparedStatement pstatement = null;
            // Create prepared statement
            pstatement = connection.prepareStatement(insertQuery.toString());

            // Set values in the prepared statement
            for (int i = 0; i < dynamicDataList.size(); i++) {
                pstatement.setString(i + 1, dynamicDataList.get(i));
            }

            // Execute the insert query
            int rowsAffected = pstatement.executeUpdate();

            // Check if the insertion was successful
            return rowsAffected > 0;
         
			
			
		} catch (SQLException e) {
            System.out.println("An error occurred while checking if the database exists: " + e.getMessage());
            return false;
        }

		
		
	}
	

}
