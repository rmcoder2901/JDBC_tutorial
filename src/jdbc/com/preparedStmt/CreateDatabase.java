package jdbc.com.preparedStmt;

import java.sql.*;
import java.util.Scanner;

public class CreateDatabase {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter database name : ");
		String db = sc.next();
		String query = "create database "+db;
		createDatabase(query);
	}

	public static void createDatabase(String query) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?useSSL=false", "root", "root");
				PreparedStatement ps = con.prepareStatement(query);) {
			ps.execute();
			System.out.println("Database created successfully ...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
