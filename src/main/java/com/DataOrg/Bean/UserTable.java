package com.DataOrg.Bean;

//THis is user table bean used to hold the 

import java.util.ArrayList;
import java.util.Optional;

public class UserTable {
	
	private String TableName;
	
	private ArrayList<String> ColumnNames;
	
	private String DbName;
	
	private Optional<String> date;
	
	public Optional<String> getDate() {
		return date;
	}
	public void setDate(Optional<String> date) {
		this.date = date;
	}
	public String getDbName() {
		return DbName;
	}
	public void setDbName(String dbName) {
		DbName = dbName;
	}
	public String getTableName() {
		return TableName;
	}
	public void setTableName(String tableName) {
		TableName = tableName;
	}

	
	public ArrayList<String> getColumnNames() {
		return ColumnNames;
	}
	public void setColumnNames(ArrayList<String> columnNames) {
		ColumnNames = columnNames;
	}
	
	

}
