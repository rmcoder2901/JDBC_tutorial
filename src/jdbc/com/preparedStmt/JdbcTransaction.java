package jdbc.com.preparedStmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTransaction {

	public static void main(String[] args) {
		String query1 = "insert into student values (?, ?, ?, ?)";
		String query2 = "update student set name = ? where sid = ?";
		batchUpdate(query1, query2);
	}

	public static void batchUpdate(String query1, String query2) {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false", "root", "root");
			PreparedStatement insert = con.prepareStatement(query1);
			PreparedStatement update = con.prepareStatement(query2);

//			disable auto commit
			con.setAutoCommit(false);

//			insert stmt
			insert.setInt(1, 109);
			insert.setString(2, "Rick");
			insert.setInt(3, 25);
			insert.setLong(4, 9999999999l);
//			insert.executeUpdate();
			insert.addBatch();
			insert.executeBatch();
			System.out.println("insert success");
			
//			committing transactions manually
//			con.commit();

//			update stmt
			update.setString(1, "Rakesh");
			update.setInt(1, 109);
//			update.executeUpdate();
			update.addBatch();
			update.executeBatch();

//			committing transactions manually
			con.commit();
			System.out.println("Transaction Commited Successfully ...!");

		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
				System.out.println("Transaction rolled back ...");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
