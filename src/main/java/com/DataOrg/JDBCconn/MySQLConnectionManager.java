package com.DataOrg.JDBCconn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionManager {
	private static Connection connection;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/DataOrg";
    private static final String USERNAME = "barath";
    private static final String PASSWORD = "Barathg@1516";


    public Connection getconnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/DataOrg?allowPublicKeyRetrieval=true&useSSL=false", "barath", "Barathg@1516");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("couldn't connect!");
            throw new RuntimeException(ex);
        }
    }
    
    //get the connection without database name
    public Connection getEmptyconnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/?allowPublicKeyRetrieval=true&useSSL=false", "barath", "Barathg@1516");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("couldn't connect!");
            throw new RuntimeException(ex);
        }
    }

}
