package com.me.freemarker.servlet;

public class Employee {
	private Integer empno;
	private String ename;
	private String department;
	private String job;
	private Float salary;
	
	
	public Employee() {
		super();
	}
	public Employee(Integer empno, String ename, String department, String job, Float salary) {
		this.empno = empno;
		this.ename = ename;
		this.department = department;
		this.job = job;
		this.salary = salary;
	}
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	
}
