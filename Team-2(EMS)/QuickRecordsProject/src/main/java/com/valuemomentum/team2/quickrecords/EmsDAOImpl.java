package com.valuemomentum.team2.quickrecords;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class  EmsDAOImpl extends DBConnection implements EmsDAO {
	Scanner sc = new Scanner(System.in);
	PreparedStatement ps = null;
	Admin admin = null;
	ResultSet rs = null;
	Statement stmt = null;

	public String addEmp() {
		int i = 1, n = 0;
		String message = null;

		try {
			while (i == 1) {
				admin = new Admin();

				System.out.println("Enter Employee first_name ?");
				admin.setFisrt_name(sc.next());
				System.out.println("Enter Employee last_name ?");
				admin.setLast_name(sc.next());	
				System.out.println("Enter Employee Designation ?");
				admin.setDesignation(sc.next());
				System.out.println("Enter Employee DoJ");
				admin.setDoj(sc.next());
				System.out.println("Enter Employee Department id");
				admin.setDept_id(sc.nextInt());
				System.out.println("Enter Employee Salary");
				admin.setSalary(sc.nextDouble());
				System.out.println("Enter Employee contact ?");
				admin.setContact(sc.next());



				ps = conn.prepareStatement(
						"insert  into `admin`(`first_name`,`last_name`,`designation`,`edoj`,`deptt_id`,`salary`,`contact`) VALUES (?,?,?,?,?,?,?)");

				ps.setString(1, admin.getFisrt_name());
				ps.setString(2, admin.getLast_name());
				ps.setString(3, admin.getDesignation());
				ps.setString(4, admin.getDoj());
				ps.setInt(5, admin.getDept_id());
				ps.setDouble(6, admin.getSalary());
				ps.setString(7, admin.getContact());


				n = ps.executeUpdate();
				System.out.println("Do you want to add more employees, press 1 or else any key.");
				System.out.println("Success : Records added.");
				i = sc.nextInt();

			}
		} catch (SQLException e) 
		{

			e.printStackTrace();
		}
		if (n != 0) {
			message = "successfully employee details added...";
		} else {
			message = "Not able to insert please try again...";
		}
		return message;

	}

	public String login(String uname, String pwd) {
		String type = null;


		try 
		{

			ps = conn.prepareStatement("select type from users where username=? and password=?");

			ps.setString(1, uname);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				type = rs.getString(1);
			}

		} catch (Exception e) {

			System.out.println("Invalid username/password...");
		}

		return type;
	}

	public Admin viewEmployee(int eid) {

		admin = new Admin();
		try {
			ps = conn.prepareStatement("select * from admin where eid=?");
			ps.setInt(1, eid);
			rs = ps.executeQuery();


			while (rs.next()) 
			{
				System.out.println("EID" + " \t \t" + "First_Name" + " \t" + "Last_Name" +" \t" + "Designation" + " \t " + "Doj" +" \t \t" +
						"Dept_id" + "\t \t" +"Salary" +"\t \t" +"Phone" );
				System.out.println("---------------------------------------------------------------------------------------------------------------------------- ");

				String first_name=rs.getString("first_name");
				String last_name=rs.getString("last_name");
				String designation=rs.getString("designation");
				String edoj=rs.getString("edoj");
				int deptt_id=rs.getInt("deptt_id");
				int salary=rs.getInt("salary");
				String contact=rs.getString("contact");

				System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n",eid,first_name,last_name,designation,edoj,deptt_id,salary,contact);

			}

		} catch (Exception e)
		{
			System.out.println("Match not found...!");
		}

		return admin;
	}

	@SuppressWarnings("unchecked")
	public  List<Admin> viewAllEmployee() {
		@SuppressWarnings("rawtypes")
		ArrayList admins = new ArrayList<Admin>();
		try {
			ps = conn.prepareStatement("select * from admin");
			rs = ps.executeQuery();
			while (rs.next()) 
			{

				int eid=rs.getInt("eid");
				String first_name=rs.getString("first_name");
				String last_name=rs.getString("last_name");
				String designation=rs.getString("designation");
				String edoj=rs.getString("edoj");
				int deptt_id=rs.getInt("deptt_id");
				int salary=rs.getInt("salary");
				String contact=rs.getString("contact");

				System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n",eid,first_name,last_name,designation,edoj,deptt_id,salary,contact);


			}

		}
		catch (Exception e) {

			System.out.println("Match not found...!");
		}
		return admins;
	}

	public  Admin viewEmployeeByDept(int d_id) 
	{
		admin = new Admin();
		try {
			ps = conn.prepareStatement("SELECT eid,first_name,last_name,designation,edoj,salary,contact,dept_name,dept_location FROM admin a INNER JOIN departments d ON a.deptt_id=d.d_id WHERE d_id= ?;");
			ps.setInt(1, d_id);
			rs = ps.executeQuery();

			System.out.println("EID" + " \t \t" + "First_Name" + " \t" + "Last_Name" +" \t" + "Designation" + " \t " + "Doj"  + "\t \t" +"Salary" +"\t \t" +"Phone" + "\t \t" + "Dept_Name"+"\t"+"Dept_Location");
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			while (rs.next()) 
			{
				int eid=rs.getInt("eid");
				String first_name=rs.getString("first_name");
				String last_name=rs.getString("last_name");
				String designation=rs.getString("designation");
				String edoj=rs.getString("edoj");
				int salary=rs.getInt("salary");
				String contact=rs.getString("contact");
				String dept_name=rs.getString("dept_name");
				String dept_location=rs.getString("dept_location");

				System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n",eid,first_name,last_name,designation,edoj,salary,contact,dept_name,dept_location);

			}

		} 

		catch (Exception e) 
		{
			System.out.println("Match not found...!");
		}
		return admin;
	}


	public String updateEmployee(int eid) {

		int n = 0;
		String result = null;
		try {
			System.out.println("Select Your Choice :  1. SALARY 2.DESIGNATION");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				ps = conn.prepareStatement("update admin set salary=? where eid=?");
				System.out.println("Enter Employee Salary ");
				int sal = sc.nextInt();
				ps.setInt(1, sal);
				ps.setInt(2, eid);
				n = ps.executeUpdate();
				break;

			case 2:
				ps = conn.prepareStatement("update admin set designation=? where eid=?");
				System.out.println("Set New Designation For Employee ");
				String designation = sc.next();
				ps.setString(1,designation);
				ps.setInt(2, eid);
				n = ps.executeUpdate();
				break;

			default:
				System.out.println("Please select Your choice range 1-2 only");
			}
			if (n != 0) {
				result = "SUCCESS : Employee details Updated....";
			} else {
				result = "ALERT : ID not found.Please try again...";
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}


	public String register(Users u) {

		String message = null;
		try {
			ps = conn.prepareStatement("insert into users(username,password,type) values(?,?,?)");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getType());
			int n = ps.executeUpdate();
			if (n != 0) {
				message = "SUCCESS : Details Registered..";
			} else {
				message = "ALERT : Enter Feilds In Proper Format...";
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return message;
	}


	public String deleteEmployee(int eid) {

		int n = 0;
		String msg = null;
		try {
			ps = conn.prepareStatement("delete from admin where eid= ?");
			ps.setInt(1, eid);
			n = ps.executeUpdate();

			if (n != 0) {
				msg = "SUCCESS : Employee details Deleted";
			} else {
				msg = "ALERT : Enter Feilds In Proper Format...";
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return msg;

	}





}
