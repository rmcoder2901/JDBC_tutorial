package jdbc.com.loginPage;

import java.util.Scanner;

public class Users {

	public static Scanner sc = new Scanner(System.in);

	public static User createAccount() {
		System.out.print("Enter Name : ");
		String name = sc.next();
		System.out.print("Enter Phone Number : ");
		long ph = sc.nextLong();
		System.out.print("Enter Role : ");
		String role = sc.next().toLowerCase();
		System.out.print("Enter ID : ");
		int id = sc.nextInt();
		System.out.print("Enter Password : ");
		String pwd = sc.next();
		User user = new User(id, pwd, name, ph, role);
		return user;
	}

}
