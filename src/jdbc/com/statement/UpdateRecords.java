package jdbc.com.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UpdateRecords {

	public static void main(String[] args) {
		String query = "update student set age = 200 where sid = 101";
		updateRecords(query);
	}

	public static void updateRecords(String query) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false", "root",
				"root"); Statement stmt = con.createStatement();) {
			int rows = stmt.executeUpdate(query);
			System.out.println("No. of rows effected : " + rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
