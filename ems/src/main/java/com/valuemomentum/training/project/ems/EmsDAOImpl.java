package com.valuemomentum.training.project.ems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class  EmsDAOImpl extends DBConnection {
	Scanner sc = new Scanner(System.in);
	//Connection con = DBConnection.getConnection();
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
				
				System.out.println("Enter Employee ID ?");
				admin.setEid(sc.nextInt());
				System.out.println("Enter Employee name ?");
				admin.setEname(sc.next());
				System.out.println("Enter Employee Salary");
				admin.setEsalary(sc.nextDouble());
				System.out.println("Enter Employee DoJ");
				admin.seteDoJ(sc.next());
				System.out.println("Create a password");
				admin.setPw(sc.next());


				ps = conn.prepareStatement(
						"insert  into `admin`(`eid`,`ename`,`esalary`,`edoj`,`epw`) VALUES (?,?,?,?,?)");

				ps.setInt(1, admin.getEid());
				ps.setString(2, admin.getEname());
				ps.setDouble(3, admin.getEsalary());
				ps.setString(4, admin.geteDoJ());
				ps.setString(5, admin.getPw());


				n = ps.executeUpdate();
				System.out.println("Do you want to add more employees, press 1 or else any key.");
				System.out.println("Success : Records added.");
				i = sc.nextInt();

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (n != 0) {
			message = "successfully doctor details added...";
		} else {
			message = "Not able to insert.please try again...";
		}
		return message;

	}

	public String login(String uname, String pwd) {
		String type = null;
		
		
		try {
//			stmt=conn.createStatement();
//			rs=stmt.executeQuery("SELECT type from users where type='admin'");
			
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
			
			while (rs.next()) {
				System.out.println("EID" + "\t" + "ENAME" + "\t" + "ESALARY" + "\t"  + "EDOJ" );
				System.out.println("----------------------------------------------------------");
				System.out.println(rs.getInt(1)+"\t "+rs.getString(2)+"\t "+rs.getDouble(3)+" \t"+
						rs.getString(4));
				System.out.println("---------------------------------------------------------- ");

			}
		} catch (Exception e) {
			
			// TODO Auto-generated catch block

			System.out.println("Match not found...!");
		}

		return admin;
	}
	
public  List<Admin> viewAllEmployee() {
	    ArrayList admins = new ArrayList<Admin>();
		//admin = new Admin();
		try {
			ps = conn.prepareStatement("select * from admin");
			//ps.setInt(1, eid);
			rs = ps.executeQuery();
			//ArrayList admins = new ArrayList<Admin>();
			while (rs.next()) {
				admin = new Admin();
				admin.setEid(rs.getInt(1));
				admin.setEname(rs.getString(2));
				admin.setEsalary(rs.getDouble(3));
				admin.seteDoJ(rs.getString(4));
				admins.add(admin);	
			}
			
		} catch (Exception e) {
			
			// TODO Auto-generated catch block

			System.out.println("Match not found...!");
		}
		return admins;
	}


public String updateDoctor(int eid) {
	
	int n = 0;
	String result = null;
	try {
		System.out.println("Select Your Choice : 1. ENAME 2.  ESALARY 3. EDOJ 4. EPW");
		int ch = sc.nextInt();
		switch (ch) {
		case 1:
			ps = conn.prepareStatement("update admin set ename=? where eid=?");
			System.out.println("Enter Employee Name ");
			String name = sc.next();
			ps.setString(1, name);
			ps.setInt(2, eid);
			n = ps.executeUpdate();
			break;
		case 2:
			ps = conn.prepareStatement("update admin set esalary=? where eid=?");
			System.out.println("Enter Employee Salary ");
			int sal = sc.nextInt();
			ps.setInt(1, sal);
			ps.setInt(2, eid);
			n = ps.executeUpdate();
			break;
		case 3:
			ps = conn.prepareStatement("update admin set edoj=? where eid=?");
			System.out.println("Enter Employee DOJ ");
			String dojj = sc.next();
			ps.setString(1, dojj);
			ps.setInt(2, eid);
			n = ps.executeUpdate();
			break;
		case 4:
			ps = conn.prepareStatement("update admin set epw=? where eid=?");
			System.out.println("Set New Password For Employee ");
			String pww = sc.next();
			ps.setString(1,pww);
			ps.setInt(2, eid);
			n = ps.executeUpdate();
			break;
			
		default:
			System.out.println("Please select Your choice range 1-4 only");
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
		//con.close();
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















