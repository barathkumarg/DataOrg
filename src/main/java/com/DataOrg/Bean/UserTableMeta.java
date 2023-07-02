package com.DataOrg.Bean;

import java.util.Optional;

public class UserTableMeta {

	private String UserEmail;
	private String UserTableName;
	private String DbName;
	private Optional<String> date;
	
	
	public UserTableMeta() {}
	
	public UserTableMeta(String userEmail, String userTableName, String dbName, Optional<String> date) {
		
		UserEmail = userEmail;
		UserTableName = userTableName;
		DbName = dbName;
		this.date = date;
	}
		
	
	public Optional<String> getDate() {
		return date;
	}
	public void setDate(Optional<String> date) {
		this.date = date;
	}

	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	public String getUserTableName() {
		return UserTableName;
	}
	public void setUserTableName(String userTableName) {
		UserTableName = userTableName;
	}
	public String getDbName() {
		return DbName;
	}
	public void setDbName(String dbName) {
		DbName = dbName;
	}
	
	
	
}
