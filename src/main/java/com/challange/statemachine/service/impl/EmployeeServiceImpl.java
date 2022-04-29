package com.challange.statemachine.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

import com.challange.statemachine.config.StateMachineInterceptor;
import com.challange.statemachine.domain.EmployeeCheckEvents;
import com.challange.statemachine.domain.EmployeeCheckStates;
import com.challange.statemachine.model.Employees;
import com.challange.statemachine.repository.EmployeeRepository;
import com.challange.statemachine.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	private final StateMachineFactory<EmployeeCheckStates, EmployeeCheckEvents> stateMachineFactory;

	StateMachine<EmployeeCheckStates, EmployeeCheckEvents> statemachine;
	private final StateMachineInterceptor stateMachineInterceptor;

	public static final String EMPLOYEE_ID_HEADER = "employee_id";

	private StateMachine<EmployeeCheckStates, EmployeeCheckEvents> build(Integer employeeId) {
		Employees employee = employeeRepository.getById(employeeId);
		statemachine = stateMachineFactory.getStateMachine(Integer.toString(employeeId)); // UUID.randomUUID()
		statemachine.stop();
		statemachine.getStateMachineAccessor().doWithAllRegions(sma -> {
			sma.addStateMachineInterceptor(stateMachineInterceptor);
			sma.resetStateMachine(new DefaultStateMachineContext(employee.getState(), null, null, null));
		});
		statemachine.start();
		return statemachine;
	}

	private void sendEvent(Integer employeeId, StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm,
			EmployeeCheckEvents employeeEvent) {
		Message msg = MessageBuilder.withPayload(employeeEvent).setHeader(EMPLOYEE_ID_HEADER, employeeId).build();

		statemachine.sendEvent(msg);
	}

	public Employees newEmployee(Employees employee) {
		return employeeRepository.save(employee);
	}

	public List<Employees> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Transactional
	@Override
	public StateMachine<EmployeeCheckStates, EmployeeCheckEvents> beginCheck(Integer employeeId) {
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = build(employeeId);
		Employees emp = employeeRepository.getById(employeeId);

		sendEvent(employeeId, sm, EmployeeCheckEvents.BEGIN_CHECK);

		return sm;
	}
	
	@Transactional
	@Override
	public StateMachine<EmployeeCheckStates, EmployeeCheckEvents> startSecurityCheck(Integer employeeId) {
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = build(employeeId);
		Employees emp = employeeRepository.getById(employeeId);

		sendEvent(employeeId, sm, EmployeeCheckEvents.SECURITY_CHECK_START);

		return sm;
	}
	
	@Transactional
	@Override
	public StateMachine<EmployeeCheckStates, EmployeeCheckEvents> startWorkpermitCheck(Integer employeeId) {
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = build(employeeId);
		Employees emp = employeeRepository.getById(employeeId);

		sendEvent(employeeId, sm, EmployeeCheckEvents.WORK_PERMIT_CHECK_START);

		return sm;
	}


	@Transactional
	@Override
	public StateMachine<EmployeeCheckStates, EmployeeCheckEvents> finishSecurityCheck(Integer employeeId) {
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = build(employeeId);
		Employees emp = employeeRepository.getById(employeeId);

		sendEvent(employeeId, sm, EmployeeCheckEvents.SECURITY_CHECK_FINISH);

		return sm;
	}

	

	@Transactional
	@Override
	public StateMachine<EmployeeCheckStates, EmployeeCheckEvents> finishWorkpermitCheck(Integer employeeId) {
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = build(employeeId);
		Employees emp = employeeRepository.getById(employeeId);

		sendEvent(employeeId, sm, EmployeeCheckEvents.WORK_PERMIT_CHECK_FINISH);

		return sm;
	}

	@Transactional
	@Override
	public StateMachine<EmployeeCheckStates, EmployeeCheckEvents> finishInitialWorkpermitCheck(Integer employeeId) {
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = build(employeeId);
		Employees emp = employeeRepository.getById(employeeId);

		sendEvent(employeeId, sm, EmployeeCheckEvents.INITIAL_WORK_PERMIT_CHECK_FINISH);

		return sm;
	}

	@Transactional
	@Override
	public StateMachine<EmployeeCheckStates, EmployeeCheckEvents> approve(Integer employeeId) {
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = build(employeeId);

		sendEvent(employeeId, sm, EmployeeCheckEvents.APPROVE);

		return sm;
	}

	@Transactional
	@Override
	public StateMachine<EmployeeCheckStates, EmployeeCheckEvents> unApprove(Integer employeeId) {
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = build(employeeId);

		sendEvent(employeeId, sm, EmployeeCheckEvents.UNAPPROVE);
		return sm;
	}

	@Transactional
	@Override
	public StateMachine<EmployeeCheckStates, EmployeeCheckEvents> active(Integer employeeId) {
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = build(employeeId);

		sendEvent(employeeId, sm, EmployeeCheckEvents.ACTIVAT);
		return sm;
	}

	@Transactional
	@Override
	public StateMachine<EmployeeCheckStates, EmployeeCheckEvents> added(Integer employeeId) {
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> sm = build(employeeId);

		sendEvent(employeeId, sm, EmployeeCheckEvents.ADDED);
		return sm;
	}

}
