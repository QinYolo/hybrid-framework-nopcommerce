package jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServerConnUtils {
	public static Connection getSQLServerConnection() {
		String hostName = "localhost";
		String sqlInstanceName = "SQLEXPRESS";
		String database = "autotesting";
		String userName = "sa";
		String password = "onlyyou764101";
		return getSQLServerConnection(hostName,sqlInstanceName,database,userName,password);
	}
	
	public static Connection getSQLServerConnection(String hostName,String sqlInstanceName,String database,String userName,String password) {
		Connection conn = null;
		try {
			String connectionURL = "jdbc:sqlserver://" + hostName + ":1433/" + ";instance=" + sqlInstanceName + ";databaseName=" + database;
			conn = DriverManager.getConnection(connectionURL, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
