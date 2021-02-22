package com.valuemomentum.training.project.ems;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;

public class DBConnection {
	public static Connection conn=null;
	
	public static Connection getConnection(){

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");


			System.out.println("connecting to database...");

			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems", "root", "valuemomentum2021");

			if (conn != null) {
				System.out.println("Connected");
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
		}
	}


