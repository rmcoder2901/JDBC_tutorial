package jdbc.com.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;

public class BatchInsert {

	public static void main(String[] args) {
		batchInsertRecords();
	}

	public static void batchInsertRecords() {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false", "root",
				"root"); Statement stmt = con.createStatement();) {

			con.setAutoCommit(false);

//			add sql queries using addBatch()
			stmt.addBatch("insert into student values (104, 'A', 10, 7459775765)");
			stmt.addBatch("insert into student values (105, 'B', 20, 7459775765)");
			stmt.addBatch("insert into student values (106, 'C', 30, 7459775765)");

			int[] count = stmt.executeBatch();

			con.commit();

			System.out.println(Arrays.toString(count));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
