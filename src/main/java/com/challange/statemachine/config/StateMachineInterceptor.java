package com.challange.statemachine.config;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import com.challange.statemachine.domain.EmployeeCheckEvents;
import com.challange.statemachine.domain.EmployeeCheckStates;
import com.challange.statemachine.model.Employees;
import com.challange.statemachine.repository.EmployeeRepository;
import com.challange.statemachine.service.impl.EmployeeServiceImpl;


@Component
public class StateMachineInterceptor extends StateMachineInterceptorAdapter<EmployeeCheckStates, EmployeeCheckEvents> {

    @Autowired
    EmployeeRepository employeeRepository;
    
    @Override
	public void preStateChange(State<EmployeeCheckStates, EmployeeCheckEvents> state, Message<EmployeeCheckEvents> message, Transition<EmployeeCheckStates,
			EmployeeCheckEvents> transition, StateMachine<EmployeeCheckStates, EmployeeCheckEvents> stateMachine) {
    	Optional.ofNullable(message).ifPresent(msg -> {
    		Optional.ofNullable(Integer.class.cast(msg.getHeaders().getOrDefault(EmployeeServiceImpl.EMPLOYEE_ID_HEADER, 0)))
    				.ifPresent(employeeId -> {
    					Employees employee = employeeRepository.getById(employeeId);
    					employee.setState(state.getId());
    					employeeRepository.save(employee);
    				});
    		
    	 });
    	
	}
   
}
