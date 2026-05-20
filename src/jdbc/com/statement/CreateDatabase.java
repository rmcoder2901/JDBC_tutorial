package jdbc.com.statement;

import java.sql.*;

public class CreateDatabase {

	public static void main(String[] args) {
		String query = "create database jdbc_demo";
		createDatabase(query);
	}

	public static void createDatabase(String query) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?useSSL=false", "root", "root");
				Statement stmt = con.createStatement();) {
			stmt.execute(query);
			System.out.println("Database created successfully ...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
