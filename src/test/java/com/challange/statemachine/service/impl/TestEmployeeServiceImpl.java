package com.challange.statemachine.service.impl;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;

import com.challange.statemachine.domain.EmployeeCheckEvents;
import com.challange.statemachine.domain.EmployeeCheckStates;
import com.challange.statemachine.model.Employees;
import com.challange.statemachine.repository.EmployeeRepository;
import com.challange.statemachine.service.EmployeeService;

@SpringBootTest
public class TestEmployeeServiceImpl {
	
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	Employees employees;
	
	@BeforeEach
	void setup() {
		employees = new Employees();
		employees.setEmpName("Neha");
		employees.setEmpId(123);
	}
	
	
	void beginCheck() {
		Employees savedEmployee = employeeService.newEmployee(employees);
		System.out.println("Saved Employees = = = "+savedEmployee.getState());
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = employeeService.beginCheck(savedEmployee.getEmpId());
		Employees empBeginCheck = employeeRepository.getById(savedEmployee.getEmpId());
		System.out.println(sm.getState().getId());
		System.out.println(empBeginCheck);
	}

}
