package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.postgresql.Driver"; 
	static final String DB_URI = "jdbc:postgresql://localhost:5432/milkyway";

	// Database credential
	static final String USER = "postgres";
	static final String PASSWORD = "postgres";

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		Connection connection = DriverManager.getConnection(DB_URI, USER, PASSWORD);
		return connection;
	}
}
