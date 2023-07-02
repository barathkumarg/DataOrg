package com.DataOrg.DAO;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.DataOrg.logger.FileLogger;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.DataOrg.Bean.UserTableMeta;

import com.DataOrg.JDBCconn.MySQLConnectionManager;

public class UserDataOperation {

	// get the user name via email from the session
	public static String getUsername(String email) {
		FileLogger filelogger = new FileLogger();

		MySQLConnectionManager mysql = new MySQLConnectionManager();

		try (Connection connection = mysql.getconnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT username FROM users WHERE email = ?")) {
			// set parameters
			statement.setString(1, email);

			// execute the query to check user already exists
			try (ResultSet resultSet = statement.executeQuery()) {

				if (resultSet.next()) {
					filelogger.logInfo("Username fetch query executed");
					return resultSet.getString("username");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "null";

	}

	// fetch the user meta Table
	public static ArrayList<UserTableMeta> getUserTableMeta(String email) {

		// fetch the user table meta in Array
		ArrayList<UserTableMeta> tablemeta = new ArrayList<>();
		MySQLConnectionManager mysql = new MySQLConnectionManager();

		try (Connection connection = mysql.getconnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT UserEmail,UserTableName,DbName,Time FROM UserTable WHERE UserEmail = ?")) {
			// set parameters
			statement.setString(1, email);
            
			//query execution
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					UserTableMeta meta = new UserTableMeta(resultSet.getString("UserEmail"),
							resultSet.getString("UserTableName"), resultSet.getString("DbName"),
							Optional.ofNullable(resultSet.getString("Time")));
					tablemeta.add(meta);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tablemeta;
	}

	// fetch the Table data of the specified user
	public static List<Map<String, Object>> getUserTable(String TableName, String DbName) {

		List<Map<String, Object>> dataList = new ArrayList<>();

		MySQLConnectionManager mysql = new MySQLConnectionManager();

		try (Connection connection = mysql.getconnection(); Statement statement = connection.createStatement()) {

			// update the db
			String useDbQuery = "USE " + DbName;
			statement.executeUpdate(useDbQuery);

			// table get query
			String query = "SELECT * FROM " + TableName;
			ResultSet resultSet = statement.executeQuery(query);

			// preprocess the data

			ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (resultSet.next()) {
				Map<String, Object> row = new HashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					Object columnValue = resultSet.getObject(i);
					row.put(columnName, columnValue);
				}
				dataList.add(row);
			}

			return dataList;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dataList;

	}
	
	
	//fetch the columnNames Alone for the user table
	public static List<String> getUserTableColumn(String TableName, String DbName) {

        List<String> columnNames = new ArrayList<>();


		MySQLConnectionManager mysql = new MySQLConnectionManager();

		try (Connection connection = mysql.getconnection(); Statement statement = connection.createStatement()) {

			// update the db
			String useDbQuery = "USE " + DbName;
			statement.executeUpdate(useDbQuery);

			// table get query
			String query = "SELECT * FROM " + TableName;
			ResultSet resultSet = statement.executeQuery(query);

			// preprocess the data

			ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();

			
				for (int i = 1; i <= columnCount; i++) {
	                String columnName = metaData.getColumnName(i);
	                columnNames.add(columnName);
	            }
			

			return columnNames;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return columnNames;

	}
	
	

}
