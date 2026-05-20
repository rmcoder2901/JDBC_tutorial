package jdbc.com.statement;

import java.sql.*;

public class SelectRecords {

	public static void main(String[] args) {
		String query = "select sid, name, age, phone from student where sid = 109";
		selectRecords(query);
	}

	public static void selectRecords(String query) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false", "root",
				"root"); Statement stmt = con.createStatement();) {
			ResultSet rs = stmt.executeQuery(query);
			boolean found = false;
			while (rs.next()) {
				int sid = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				long phone = rs.getLong(4);

				System.out.println("Sid : " + sid);
				System.out.println("Name : " + name);
				System.out.println("Age : " + age);
				System.out.println("Phone : " + phone);
				found = true;
			}
			if (!found)
				System.out.println("Record not found ...!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
