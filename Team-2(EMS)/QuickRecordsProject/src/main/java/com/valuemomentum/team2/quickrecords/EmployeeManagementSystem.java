package com.valuemomentum.team2.quickrecords;

import java.util.Scanner;
import java.util.regex.Pattern;

public class EmployeeManagementSystem 
{
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		EmsDAOImpl emsdaoi = new EmsDAOImpl();
		@SuppressWarnings("unused")
		Admin a = new Admin();
		Users u = new Users();


		DBConnection.getConnection();

		while (true) {
			 try {

			System.out.println("**************************************************");
			System.out.println("|    QUICK RECORDS (Employee Management System)  |");
			System.out.println("**************************************************");
			System.out.println("|  1.  LOGIN                                     |");
			System.out.println("|  2.  REGISTER                                  |");
			System.out.println("|  3.  EXIT                                      |");
			System.out.println("**************************************************");
			System.out.println();
			System.out.println("Enter Your Choice ?");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter User Name ?");
				String name = sc.next();
				if (Pattern.matches("[A-Za-z0-9]{3,10}", name)) 
				{
					u.setUsername(name);
					//System.err.println("please enter name in alphabets");
				}else {
					System.err.println("Invalid Username...try again...!");
					main(null);
				}

				//u.setUsername(name);

				//u.setUsername(name);
				System.out.println("Enter Password ?");
				String password = sc.next();
				if (Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", password)) {
					u.setPassword(password);
					//System.err.println("please enter valid password minimum of 8");
				}else {
					System.err.println("Invalid Password...try again...!");
					main(null);
				}

				u.setPassword(password);

				try {
					String type = emsdaoi.login(name, password);
					if (type.equals("admin")) {
						System.out.println();
						System.out.println("SUCCESS : ");
						System.out.println("Logged in as ADMIN");
						Menu.adminMenu();


					} 
					else if (type.equals("user")) 
					{
						System.out.println();
						System.out.println("SUCCESS : ");
						System.out.println("Logged in as USER");
						Menu.users();

					} 
					else 
					{
						System.err.println("Invalid UserName / PassWord");
					}
				} 
				catch (Exception e) 
				{

					System.out.println();
					System.err.println("ALERT : ");
					System.err.println("Invalid username/password...");
					System.out.println();
				}
				break;
			case 2:
				System.out.println("Enter User Name ?");
				String namee = sc.next();

				if (Pattern.matches("[a-zA-Z]{3,10}", namee)) 
				{
					u.setUsername(namee);
					//System.err.println("please enter the valid username with in the range of 1-14 ");
					// name = sc.next();
					//main(args);

				}else {
					System.err.println("Instructions : Length at least 3 characters,"
							+ "\n\t       and maximum length of 10 ");
					main(null);
				}

				//u.setUsername(namee);

				System.out.println("Enter Password ?");
				String passwordd = sc.next();
				if (Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", passwordd)) {
					u.setPassword(passwordd);

					//System.err.println("please enter valid password max of 8 characters");
					// password = sc.next();
				}
				else {
					System.err.println("Instructions : Minimum eight characters, at least "
							+ "\n\tone uppercase letter, "
							+ "\n\tone lowercase letter, "
							+ "\n\tone number and "
							+ "\n\tone special character");
					main(null);
				}
				//u.setPassword(passwordd);

				System.out.println("Enter type : admin / user.");
				u.setType(sc.next());

				String message = emsdaoi.register(u);
				System.out.println();
				System.out.println(message);
				System.out.println();

				break;
			case 3:
				System.out.println("-------THANK YOU--------");
				System.exit(0);
				break;
			default:
				System.err.println("ALERT : Choose from range 1-3.");




			}
		}
		catch(Exception e) {
			System.err.println("ALERT : Choose from range 1-3.");
			main(null);
		}
	}


}}
