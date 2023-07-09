package com.DataOrg.JDBCconn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.DataOrg.logger.FileLogger;



public class MySQLConnectionManager {
	 FileLogger logger = new FileLogger();
	private static MySQLConnectionManager instance;
	private static Connection connection;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/DataOrg";
    private static final String USERNAME = "barath";
    private static final String PASSWORD = "Barathg@1516";
   
    
    private MySQLConnectionManager() {
        // Private constructor to prevent instantiation
    }

    public static MySQLConnectionManager getInstance() {
        if (instance == null) {
            synchronized (MySQLConnectionManager.class) {
                if (instance == null) {
                    instance = new MySQLConnectionManager();
                }
            }
        }
        return instance;
    }


    //get the connection for the DataOrg connection
    public Connection getconnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/DataOrg?allowPublicKeyRetrieval=true&useSSL=false", "barath", "Barathg@1516");
        }catch(Exception ex){
           logger.logInfo(ex.getMessage());
           logger.logInfo("couldn't connect!");
            throw new RuntimeException(ex);
        }
    }
    
    //get the connection without database name, empty connection
    public Connection getEmptyconnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/?allowPublicKeyRetrieval=true&useSSL=false", "barath", "Barathg@1516");
        }catch(Exception ex){
        	logger.logInfo(ex.getMessage());
        	logger.logInfo("couldn't connect!");
            throw new RuntimeException(ex);
        }
    }

}
