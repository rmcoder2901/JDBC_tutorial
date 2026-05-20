package jdbc.com.loginPage;

import java.sql.*;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		String q1 = "insert into user values (?, ?, ?, ?, ?)";
		String q2 = "select * from user where id = ? and pwd = ? and role = ?";
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSl=false", "root",
				"root");
				PreparedStatement reg = con.prepareStatement(q1);
				PreparedStatement log = con.prepareStatement(q2);
				Scanner sc = new Scanner(System.in);) {
			System.out.println("1. Register\n2. Login");
			int ch = sc.nextInt();
			switch (ch) {
			case 1 -> {
				User user = Users.createAccount();
				reg.setInt(1, user.getId());
				reg.setString(2, user.getPwd());
				reg.setString(3, user.getName());
				reg.setLong(4, user.getPh());
				reg.setString(5, user.getRole());
				int rows = reg.executeUpdate();
				if (rows > 0)
					System.out.println("Registration successfull ...");
				else
					System.out.println("Registration failed ...");
			}
			case 2 -> {
				System.out.print("Enter Login Id : ");
				log.setInt(1, sc.nextInt());
				System.out.print("Enter Login Password : ");
				log.setString(2, sc.next());
				System.out.print("Enter Role : ");
				log.setString(3, sc.next().toLowerCase());
				ResultSet rs = log.executeQuery();
				boolean check = false;
				while (rs.next()) {
					System.out.println("Login successfull ...");
					check = true;
					if (rs.getString(5).equalsIgnoreCase("admin")) {
						System.out.println("***** Welcome to Admin Dashboard *****");
						boolean login = true;
						while (login) {
							System.out.println("1. Users Details\n2. Update\n3. Delete\n4. Logout");
							ch = sc.nextInt();
							switch (ch) {
							case 1 -> {
								PreparedStatement ps = con.prepareStatement("select * from user where id = ?");
								System.out.println("Enter ID : ");
								int id = sc.nextInt();
								ps.setInt(1, id);
								ResultSet details = ps.executeQuery();
								boolean flag = false;
								while (details.next()) {
									System.out.println("******* User Details *******");
									System.out.println("ID : " + details.getInt(1));
									System.out.println("Name : " + details.getString(3));
									System.out.println("Phone : " + details.getLong(4));
									System.out.println("Role : " + details.getString(5));
									flag = true;
								}
								if (!flag)
									System.out.println("User not found ...");
							}
							case 2 -> {
								PreparedStatement ps = con.prepareStatement("update user set name = ? where id = ?");
								System.out.println("Enter ID : ");
								int id = sc.nextInt();
								System.out.println("Enter Name : ");
								String name = sc.next();
								ps.setString(1, name);
								ps.setInt(2, id);
								int rows = ps.executeUpdate();
								if (rows > 0)
									System.out.println("User updated successfully ...");
								else
									System.out.println("User not found ...");
							}
							case 3 -> {
								PreparedStatement ps = con.prepareStatement("delete from user where id = ?");
								System.out.println("Enter ID : ");
								int id = sc.nextInt();
								ps.setInt(1, id);
								int rows = ps.executeUpdate();
								if (rows > 0)
									System.out.println("User deleted successfully ...");
								else
									System.out.println("User not found ...");
							}
							case 4 -> {
								System.out.println("Logout successfully ...");
								login = false;
							}
							}
						}
					}

					else if (rs.getString(5).equalsIgnoreCase("user")) {
						System.out.println("***** Welcome to User Dashboard *****");
						boolean login = true;
						while (login) {
							System.out.println("1.User Details\n2. Logout");
							ch = sc.nextInt();
							switch (ch) {
							case 1 -> {
								PreparedStatement ps = con
										.prepareStatement("select * from user where id = ? and role = 'user'");
								System.out.println("Enter ID : ");
								int id = sc.nextInt();
								ps.setInt(1, id);
								ResultSet details = ps.executeQuery();
								boolean flag = false;
								while (details.next()) {
									System.out.println("******* User Details *******");
									System.out.println("ID : " + details.getInt(1));
									System.out.println("Name : " + details.getString(3));
									System.out.println("Phone : " + details.getLong(4));
									System.out.println("Role : " + details.getString(5));
									flag = true;
								}
								if (!flag)
									System.out.println("User not found ...");
							}
							case 2 -> {
								System.out.println("Logout successfully ...");
								login = false;
							}
							}
						}
					}
				}
				if (!check)
					System.out.println("Login failed ...");
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
