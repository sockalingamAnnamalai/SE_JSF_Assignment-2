package SEassign2;

import java.sql.Connection;
import java.sql.SQLException;

public class DBcon {
	
	// Opening a Connection
	public static Connection getConnection() {
	
		try {
		Connection con = null;
		
		// Setup the DataSource object
		com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
		ds.setServerName("localhost");
		ds.setPortNumber(3306);
		ds.setDatabaseName("Seasign2");
		ds.setUser("root");
		ds.setPassword("2509");
		
		// Get a connection object
		con = ds.getConnection();
		return con;
		} catch(Exception e) {
			System.err.println("Login Exception: " + e.getMessage());
			return null;
		}
	}
	
	//Closing a Connection
	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		}
	}
}