package jdbc.com.statement;

import java.sql.*;

public class CreateTable {
	public static void main(String[] args) {
//		String query = "create table student (sid int(3) primary key auto_increment, name varchar(20), age int(3), phone bigint(10));";
		String query="create table user (id int(3) primary key auto_increment, pwd varchar(20), name varchar(20), ph bigint(10), role varchar(20))";
		createDatabase(query);
	}

	public static void createDatabase(String query) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false", "root", "root");
				Statement stmt = con.createStatement();) {
			stmt.execute(query);
			System.out.println("Table created successfully ...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
