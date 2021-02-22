package com.valuemomentum.training.project.ems;

public class Admin {
	private int eid;
	private String ename;
	private double esalary;
	private String eDoJ;
	private String pw;


	Admin(){

	}
	public Admin(int eid, String ename, double esalary, String eDoJ,String pw) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.esalary = esalary;
		this.eDoJ = eDoJ;
		this.pw=pw;
	}


	public int getEid() {
		return eid;
	}


	public void setEid(int eid) {
		this.eid = eid;
	}


	public String getEname() {
		return ename;
	}


	public void setEname(String ename) {
		this.ename = ename;
	}


	public double getEsalary() {
		return esalary;
	}


	public void setEsalary(double esalary) {
		this.esalary = esalary;
	}


	public String geteDoJ() {
		return eDoJ;
	}


	public void seteDoJ(String eDoJ) {
		this.eDoJ = eDoJ;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}


	@Override
	public String toString() {
		return  eid +"\t"+ ename+"\t" + esalary+"\t" + eDoJ ;
	}





}
