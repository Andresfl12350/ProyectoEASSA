package ec.com.eeasa.sendmail.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	public static Connection dbConnection = null;
	
	public ConnectionManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static Connection getConnection() {
		dbConnection=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");	
			dbConnection=DriverManager.getConnection(
			"jdbc:oracle:thin:@172.20.35.209:1521/testorcl","ROOTSISGERH","rootsisgerh1");				
		}catch(Exception e){ 
			System.out.println(e);
		}
		return dbConnection;
	}	
	
	public static void closeConecction(){
		try {
			if(dbConnection!=null && !dbConnection.isClosed()){
				dbConnection.close();
				dbConnection=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
