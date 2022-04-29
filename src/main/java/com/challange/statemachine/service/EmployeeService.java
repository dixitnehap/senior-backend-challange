package com.challange.statemachine.service;

import java.util.List;

import org.springframework.statemachine.StateMachine;

import com.challange.statemachine.domain.EmployeeCheckEvents;
import com.challange.statemachine.domain.EmployeeCheckStates;
import com.challange.statemachine.model.Employees;

public interface EmployeeService {

	
	Employees newEmployee(Employees employees);
	
	public List<Employees> getAllEmployees();
	
	StateMachine<EmployeeCheckStates, EmployeeCheckEvents> added(Integer employeeId);
	
	StateMachine<EmployeeCheckStates, EmployeeCheckEvents> beginCheck(Integer employeeId);
	
	StateMachine<EmployeeCheckStates, EmployeeCheckEvents> startSecurityCheck(Integer employeeId);
	
	StateMachine<EmployeeCheckStates, EmployeeCheckEvents> startWorkpermitCheck(Integer employeeId);
	
	
	
	StateMachine<EmployeeCheckStates, EmployeeCheckEvents> finishSecurityCheck(Integer employeeId);
	
	StateMachine<EmployeeCheckStates, EmployeeCheckEvents> finishWorkpermitCheck(Integer employeeId);
	
	StateMachine<EmployeeCheckStates, EmployeeCheckEvents> finishInitialWorkpermitCheck(Integer employeeId);
	
	StateMachine<EmployeeCheckStates, EmployeeCheckEvents> approve(Integer employeeId);
	
	StateMachine<EmployeeCheckStates, EmployeeCheckEvents> unApprove(Integer employeeId);
	
	StateMachine<EmployeeCheckStates, EmployeeCheckEvents> active(Integer employeeId);
	

}
