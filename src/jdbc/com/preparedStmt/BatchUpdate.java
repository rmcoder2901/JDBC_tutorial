package jdbc.com.preparedStmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BatchUpdate {
	public static void main(String[] args) {
		String query = "update student set name = ? where sid = ?";
		batchUpdate(query);
	}

	public static void batchUpdate(String query) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false", "root",
				"root"); PreparedStatement ps = con.prepareStatement(query);) {

//			disable auto commit
			con.setAutoCommit(false);

			ps.setInt(2, 107);
			ps.setString(1, "AAA");
//			add to batch here
			ps.addBatch();

			ps.setInt(2, 108);
			ps.setString(1, "BBB");
//			add to batch here
			ps.addBatch();

			ps.setInt(2, 109);
			ps.setString(1, "CCC");
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
