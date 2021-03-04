package com.valuemomentum.team2.quickrecords;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class Menu {
	static Scanner sc = new Scanner(System.in);
	static EmsDAOImpl emsdaoimpl = new EmsDAOImpl();
	static Admin ad = new Admin();
	static PreparedStatement ps = null;
	static ResultSet rs = null;

	public static void adminMenu() {
		System.out.println("------------------------------------------------------ ");
		System.out.println("|                 ADMIN MAIN MENU                     |");
		System.out.println("-------------------------------------------------------");
		System.out.println("| 1.Create A New Employee Profile                     |");
		System.out.println("| 2.View Employee Profile                             |");
		System.out.println("| 3.View All Employees                                |");
		System.out.println("| 4.View Employees Profile of Particular Department   |");
		System.out.println("| 5.Update Employee Profile                           |");
		System.out.println("| 6.Delete Employee Profile                           |");
		System.out.println("| 7.Back                                              |");
		System.out.println("-------------------------------------------------------");
		System.out.println("Enter Your Choice ?");
		int choice = sc.nextInt();
		switch (choice) {
		
		case 1:
			emsdaoimpl.addEmp();
			adminMenu();
			break;
		case 2:
			
				System.out.println("Enter Employee Id ?");
				emsdaoimpl.viewEmployee(sc.nextInt());
				adminMenu();
				break;
		case 3:
			System.out.println("EID" + " \t \t" + "First_Name" + " \t" + "Last_Name" +" \t" + "Designation" + " \t " + "Doj" +" \t \t" +
					"Dept_id" + "\t \t" +"Salary" +"\t \t" +"Phone" );
					System.out.println("-------------------------------------------------------------------------------------------------------------------------");
			List<Admin> admins = emsdaoimpl.viewAllEmployee();
			for (Admin d : admins) {
				System.out.println(d);
			}
			adminMenu();
			break;
		case 4:
			System.out.println("Enter Department ID ?");
			emsdaoimpl.viewEmployeeByDept(sc.nextInt());
			adminMenu();
			break;
			
		case 5:
			System.out.println("Enter Updating Employee Id ?");
			ad.setEid(sc.nextInt());
			String message = emsdaoimpl.updateEmployee(ad.getEid());
            System.out.println(message);
			adminMenu();
			break;
		case 6:
			System.out.println("Enter Deleting Employee Id ?");
			ad.setEid(sc.nextInt());
			String msg=emsdaoimpl.deleteEmployee(ad.getEid());
			System.out.println(msg);
			adminMenu();
			break;
		case 7:
			EmployeeManagementSystem.main(null);
		default:
			System.out.println("Please select your choice range 1-7 only");
			adminMenu();

		}
	}
	
	public static void users() {
		System.out.println("--------------------------------------");
		System.out.println("|           USERS MAIN MENU          |");
		System.out.println("--------------------------------------");

		System.out.println("| 1.View My Profile                  |");
		System.out.println("| 2.View Emplolyees in My Department |");
		System.out.println("| 3.Back                             |");

		System.out.println("--------------------------------------");
		System.out.println("Enter Your Choice ?");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Enter Your ID ?");
			emsdaoimpl.viewEmployee(sc.nextInt());
			users();
			break;
		case 2:
			System.out.println("Enter Department ID ?");
			emsdaoimpl.viewEmployeeByDept(sc.nextInt());
			users();
			
		case 3:
			EmployeeManagementSystem.main(null);
		default:
			System.out.println("Please select your choice range 1-3 only");
			users();

		}
		
	}

	
		
		
		
}
