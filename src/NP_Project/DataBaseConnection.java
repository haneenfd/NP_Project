package NP_Project;

import java.sql.*;

public class DataBaseConnection {
	
	static final String DB_URL = "jdbc:mysql://localhost/test";
	static final String USER = "root";
	static final String PASS = "haneen";

         public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        return connection;
    }
	public static void main(String[] args) {
		// Open a connection
		
	}
}