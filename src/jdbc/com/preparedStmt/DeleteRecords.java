package jdbc.com.preparedStmt;

import java.sql.*;
import java.util.Scanner;

public class DeleteRecords {
	public static void main(String[] args) {
		String query = "delete from student where sid = ?";
		deleteRecords(query);
	}

	public static void deleteRecords(String query) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false", "root",
				"root"); PreparedStatement ps = con.prepareStatement(query); Scanner sc = new Scanner(System.in);) {
			System.out.print("Enter Sid : ");
			ps.setInt(1, sc.nextInt());
			int rows = ps.executeUpdate();
			System.out.println("No. of rows effected : " + rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
