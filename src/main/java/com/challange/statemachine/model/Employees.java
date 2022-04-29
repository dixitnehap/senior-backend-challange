package com.challange.statemachine.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.challange.statemachine.domain.EmployeeCheckStates;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "EMPLOYEES")
public class Employees {
	@Id
	@Column
	private int empId;
	
	@Column
	private String empName;
	
	@Column
	@Enumerated(EnumType.STRING)
	private EmployeeCheckStates state;
	

	

	public EmployeeCheckStates getState() {
		return state;
	}

	public void setState(EmployeeCheckStates state) {
		this.state = state;
	}

	public Employees(int empid, String empName, EmployeeCheckStates state) {
		this.empId = empid;
		this.empName = empName;
		this.state = state;
	}
	
	public Employees() {  
	}  
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	

}

