package jdbc.com.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;

public class BatchUpdate {
	public static void main(String[] args) {
		batchUpdate();
	}

	public static void batchUpdate() {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false", "root",
				"root"); Statement stmt = con.createStatement();) {

			con.setAutoCommit(false);

//			add sql queries using addBatch()
			stmt.addBatch("update student set name = 'A' where name = 'A'");
			stmt.addBatch("update student set age = 21 where name = 'B'");
			stmt.addBatch("update student set phone = 9876543210 where sid = 106");

			int[] count = stmt.executeBatch();

			con.commit();

			System.out.println(Arrays.toString(count));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
