package com.challange.statemachine.config;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import com.challange.statemachine.domain.EmployeeCheckEvents;
import com.challange.statemachine.domain.EmployeeCheckStates;

@SpringBootTest
public class StateMachineConfigTest {
	
	@Autowired
	StateMachineFactory<EmployeeCheckStates, EmployeeCheckEvents> stateMachineFactory;

	@Test
	void testStateMachine() {
		StateMachine<EmployeeCheckStates, EmployeeCheckEvents> stateMachine = stateMachineFactory.getStateMachine(UUID.randomUUID());
		   stateMachine.start();
	       stateMachine.sendEvent(EmployeeCheckEvents.BEGIN_CHECK);
	       stateMachine.sendEvent(EmployeeCheckEvents.APPROVE);
	       stateMachine.sendEvent(EmployeeCheckEvents.ACTIVAT);
	       stateMachine.stop();
	}
	
}
