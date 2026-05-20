package jdbc.com.statement;

import java.sql.*;

public class InsertRecords {

	public static void main(String[] args) {
		String name = "Rahul";
		int sid = 103;
		int age = 20;
		long phone = 8456745458l;
		String query = "insert into student values (" + sid + ", '" + name + "', " + age + ", " + phone + ")";
//		String query = "insert into student values (102, 'Krishna', 100, 7459775765)";
//		String query = "insert into student (age, phone) values (26,  7655575785)";
		insertRecords(query);
	}

	public static void insertRecords(String query) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false", "root",
				"root"); Statement stmt = con.createStatement();) {
			int rows = stmt.executeUpdate(query);
			System.out.println("No. of rows effected : " + rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
