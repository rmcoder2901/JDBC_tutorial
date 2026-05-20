package jdbc.com.preparedStmt;

import java.sql.*;

public class BatchInsert {
	public static void main(String[] args) {
		String query = "insert into student values (?, ?, ?, ?)";
		batchInsertRecords(query);
	}

	public static void batchInsertRecords(String query) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false", "root",
				"root"); PreparedStatement ps = con.prepareStatement(query);) {

			con.setAutoCommit(false);

			ps.setInt(1, 107);
			ps.setString(2, "AA");
			ps.setInt(3, 10);
			ps.setLong(4, 1111111111l);
//			add to batch here
			ps.addBatch();

			ps.setInt(1, 108);
			ps.setString(2, "BB");
			ps.setInt(3, 10);
			ps.setLong(4, 2222222222l);
//			add to batch here
			ps.addBatch();

			ps.setInt(1, 109);
			ps.setString(2, "CC");
			ps.setInt(3, 10);
			ps.setLong(4, 3333333333l);
//			add to batch here
			ps.addBatch();

			int[] count = ps.executeBatch();

			con.commit();

			System.out.println("No. of rows effected : "+count.length);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No. of rows effected : 0");
		}
	}
}
