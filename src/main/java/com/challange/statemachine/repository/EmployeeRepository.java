package com.challange.statemachine.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.challange.statemachine.model.Employees;

public interface EmployeeRepository extends JpaRepository<Employees, Integer>{

}
