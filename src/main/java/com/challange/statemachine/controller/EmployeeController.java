package com.challange.statemachine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challange.statemachine.domain.EmployeeCheckEvents;
import com.challange.statemachine.domain.EmployeeCheckStates;
import com.challange.statemachine.model.Employees;
import com.challange.statemachine.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@GetMapping
	public List<Employees> getEmployees() {
		return employeeService.getAllEmployees();
	}
	
	@PostMapping("/add")
	public ResponseEntity<Employees> add(@RequestBody Employees employee){
		Employees emp = employeeService.newEmployee(employee);
		return new ResponseEntity<Employees>(emp, HttpStatus.OK);
	}
	
	@PutMapping("/beginCheck/{empId}")
	public ResponseEntity<String> beginCheck(@PathVariable("empId") int empId){
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = employeeService.beginCheck(empId);
		return new ResponseEntity<String>(sm.getState().getId().toString(), HttpStatus.OK);
	}
	@PutMapping("/startSecurityCheck/{empId}")
	public ResponseEntity<String> startSecurityCheck(@PathVariable("empId") int empId){
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = employeeService.startSecurityCheck(empId);
		return new ResponseEntity<String>(sm.getState().getId().toString(), HttpStatus.OK);
	}
	@PutMapping("/startWorkpermitCheck/{empId}")
	public ResponseEntity<String> starWorkpermitCheck(@PathVariable("empId") int empId){
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = employeeService.startWorkpermitCheck(empId);
		return new ResponseEntity<String>(sm.getState().getId().toString(), HttpStatus.OK);
	}
	
	@PutMapping("/finishSecurityCheck/{empId}")
	public ResponseEntity<String> finishSecurityCheck(@PathVariable("empId") int empId){
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = employeeService.finishSecurityCheck(empId);
		return new ResponseEntity<String>(sm.getState().getId().toString(), HttpStatus.OK);
	}
	
	@PutMapping("/finishWorkpermitCheck/{empId}")
	public ResponseEntity<String> finishWorkpermitCheck(@PathVariable("empId") int empId){
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = employeeService.finishWorkpermitCheck(empId);
		return new ResponseEntity<String>(sm.getState().getId().toString(), HttpStatus.OK);
	}
	
	@PutMapping("/finishInitialWorkpermitCheck/{empId}")
	public ResponseEntity<String> finishInitialWorkpermitCheck(@PathVariable("empId") int empId){
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = employeeService.finishInitialWorkpermitCheck(empId);
		return new ResponseEntity<String>(sm.getState().getId().toString(), HttpStatus.OK);
	}
	
	@PutMapping("/approve/{empId}")
	public ResponseEntity<String> approve(@PathVariable("empId") int empId){
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = employeeService.approve(empId);
		return new ResponseEntity<String>(sm.getState().getId().toString(), HttpStatus.OK);
	}
	
	@PutMapping("/unapprove/{empId}")
	public ResponseEntity<String> unapprove(@PathVariable("empId") int empId){
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = employeeService.unApprove(empId);
		return new ResponseEntity<String>(sm.getState().getId().toString(), HttpStatus.OK);
	}
	
	
}
