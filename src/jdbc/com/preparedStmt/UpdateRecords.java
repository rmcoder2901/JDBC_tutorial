package jdbc.com.preparedStmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateRecords {
	public static void main(String[] args) {
		String query = "update student set age = ? where sid = ?";
		updateRecords(query);
	}

	public static void updateRecords(String query) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false", "root",
				"root"); PreparedStatement ps = con.prepareStatement(query); Scanner sc = new Scanner(System.in);) {
			System.out.print("Enter Sid to Update Details : ");
			ps.setInt(2, sc.nextInt());
			System.out.print("Enter New Age : ");
			ps.setInt(1, sc.nextInt());
			int rows = ps.executeUpdate();
			System.out.println("No. of rows effected : " + rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
