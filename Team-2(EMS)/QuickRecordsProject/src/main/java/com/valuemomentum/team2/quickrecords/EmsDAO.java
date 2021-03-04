package com.valuemomentum.team2.quickrecords;

import java.util.List;

public interface EmsDAO {
	
	public String addEmp();
	public String login(String uname, String pwd);
	public Admin viewEmployee(int eid);
	public  List<Admin> viewAllEmployee();
	public  Admin viewEmployeeByDept(int d_id);
	public String updateEmployee(int eid);
	public String register(Users u);
	public String deleteEmployee(int eid);

}
