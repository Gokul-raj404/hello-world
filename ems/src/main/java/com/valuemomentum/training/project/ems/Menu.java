package com.valuemomentum.training.project.ems;

//import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class Menu {
	static Scanner sc = new Scanner(System.in);
	static EmsDAOImpl emsdaoimpl = new EmsDAOImpl();
	static Admin ad = new Admin();
	//static Connection con = DBConnection.getConnection();
	static PreparedStatement ps = null;
	static ResultSet rs = null;

	public static void adminMenu() {
		System.out.println("-----------------------");
		System.out.println("|   ADMIN MAIN MENU   |");
		System.out.println("-----------------------");
		System.out.println("| 1.Add Employee        |");
		System.out.println("| 2.View Employee       |");
		System.out.println("| 3.View All Employee   |");
		System.out.println("| 4.Update Employee     |");
		System.out.println("| 5.Delete Employee     |");
		System.out.println("| 6.Back                |");
		System.out.println("-----------------------");
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
//			System.out.println("EID" + "\t" + "ENAME" + "\t" + "ESALARY" + "\t"  + "EDOJ" );
//			System.out.println("----------------------------------------------------------");
			//System.out.println(admin);
			adminMenu();
			break;
		case 3:
			List<Admin> admins = emsdaoimpl.viewAllEmployee();
			System.out.println("EID" + "\t" + "ENAME" + "\t" + "ESALARY" + "\t"  + "EDOJ");
			System.out.println("-----------------------------------------------------------");
			for (Admin d : admins) {
				System.out.println(d);
			}
			adminMenu();
			break;
		case 4:
			System.out.println("Enter Updating Employee Id ?");
			ad.setEid(sc.nextInt());
			String message = emsdaoimpl.updateDoctor(ad.getEid());
            System.out.println(message);
			adminMenu();
			break;
		case 6:
			EmployeeManagementSystem.main(null);
		default:
			System.out.println("Please select your choice range 1-5 only");
			adminMenu();

		}
	}
	
	public static void users() {
		System.out.println("-----------------------");
		System.out.println("|   USERS MAIN MENU   |");
		System.out.println("-----------------------");

		System.out.println("| 1.View Employee      |");
		System.out.println("| 2.View all Employee |");
		System.out.println("| 3.Back              |");

		System.out.println("-----------------------");
		System.out.println("Enter Your Choice ?");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
//			System.out.println("Enter PATIENT Id ?");
//			Patient patient = hmsdao.viewPatient(sc.nextInt());
//			System.out.println("PID" + "\t" + "PNAME" + "\t" +"ADDRESS" + "\t" + "AGE" + "\t" + "GENDER"+"\t" + "DISEASE"+ "\t" +"BLOOD GROUP");
//			System.out.println("--------------------------------------------------------------------------------------------------------------");
//			System.out.println(patient);
//			Doctor();
			System.out.println("View Employee");
			break;
		case 3:
			EmployeeManagementSystem.main(null);
		default:
			System.out.println("Please select your choice range 1-3 only");
			users();

		}
		
	}

	
		
		
		
}
