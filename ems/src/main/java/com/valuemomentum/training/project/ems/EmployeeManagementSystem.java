package com.valuemomentum.training.project.ems;

import java.util.Scanner;
import java.util.regex.Pattern;
//import java.util.regex.Matcher;

public class EmployeeManagementSystem {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		EmsDAOImpl emsdaoi = new EmsDAOImpl();
		Admin a = new Admin();
		Users u = new Users();
		

		DBConnection.getConnection();
		
		while (true) {
			// try {
			
			System.out.println("******************************");
			System.out.println("| EMPLOYEE MANAGEMENT SYSTEM |");
			System.out.println("******************************");
			System.out.println("|  1.  LOGIN              |");
			System.out.println("|  2.  REGISTER                |");
			System.out.println("|  3.  EXIT                  |");
			System.out.println("******************************");
			System.out.println();
			System.out.println("Enter Your Choice ?");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				// Users u=new Users();
//				System.out.println("|  1.  REGISTER               |");
//				System.out.println("|  2.  LOGIN                  |");
//				System.out.println("|  3.  BACK                   |");
//				int d1 = sc.nextInt();
//				if (d1 == 2) {
					System.out.println("Enter User Name ?");
					String name = sc.next();
					while (!Pattern.matches("[A-Za-z0-9]{1,14}", name)) {
						System.err.println("plese enter name in alphabets");
					}
					u.setUsername(name);
					System.out.println("Enter Password ?");
					String password = sc.next();
					while (!Pattern.matches("[0-9a-zA-Z]{1,8}", password)) {
						System.err.println("please enter valid password minimum of 8");
					}
					u.setPassword(password);

					try {
						String type = emsdaoi.login(name, password);
						if (type.equals("admin")) {
							System.out.println();
							System.out.println("SUCCESS : ");
							System.out.println("Logged in as ADMIN");
							// HospitalDetails.adminMenu();
							Menu.adminMenu();


						} else if (type.equals("user")) {
							System.out.println();
							System.out.println("SUCCESS : ");
							System.out.println("Logged in as USER");
							Menu.users();

						} else {
							System.out.println("Invalid UserName / PassWord");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println();
						System.out.println("ALERT : ");
						System.out.println("Invalid username/password...");
						System.out.println();
					}
				
			}
		}
	}
}

