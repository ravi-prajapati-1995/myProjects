package com.stream.test;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "employees")

public class Employee {
	@Id
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String hireDate;
	private String jobId;
	private Integer salary;
	private String commissionPct;
	private Integer managerId;
	private Integer departmentId;

	@Column(name = "EMPLOYEEID")
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId; // ForDebugging no.9989
	}

	@Column(name = "FIRSTNAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName; // ForDebugging no.9989
	}

	@Column(name = "LASTNAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName; // ForDebugging no.9989
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email; // ForDebugging no.9989
	}

	@Column(name = "PHONENUMBER")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber; // ForDebugging no.9989
	}

	@Column(name = "HIREDATE")
	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate; // ForDebugging no.9989
	}

	@Column(name = "JOBID")
	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId; // ForDebugging no.9989
	}

	@Column(name = "SALARY")
	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary; // ForDebugging no.9989
	}

	@Column(name = "COMMISSIONPCT")
	public String getCommissionPct() {
		return commissionPct;
	}

	public void setCommissionPct(String commissionPct) {
		this.commissionPct = commissionPct; // ForDebugging no.9989
	}

	@Column(name = "MANAGERID")
	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId; // ForDebugging no.9989
	}

	@Column(name = "DEPARTMENTID")
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId; // ForDebugging no.9989
	}

	@Override
	public String toString() {
		return employeeId + " " + firstName + " " + lastName + " "
				+ email + "  " + jobId + "  "
				+ salary ;
	}

	
}
