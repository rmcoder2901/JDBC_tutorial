package jdbc.com.preparedStmt;

import java.sql.*;
import java.util.Scanner;

public class InsertRecords {

	public static void main(String[] args) {
		String query = "insert into student values (?,?,?,?)";
//		String query = "insert into student (age, phone) values (26,  7655575785)";
		insertRecords(query);
	}

	public static void insertRecords(String query) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false", "root","root"); 
				PreparedStatement ps = con.prepareStatement(query); 
				Scanner sc = new Scanner(System.in);) 
		{
			System.out.print("Enter Sid : ");
			ps.setInt(1, sc.nextInt());
			System.out.print("Enter Name : ");
			ps.setString(2, sc.next());
			System.out.print("Enter Age : ");
			ps.setInt(3, sc.nextInt());
			System.out.print("Enter Phone Number : ");
			ps.setLong(4, sc.nextLong());
			
			int rows = ps.executeUpdate();
			System.out.println("No. of rows effected : " + rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
