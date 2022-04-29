package com.challange.statemachine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import com.challange.statemachine.domain.EmployeeCheckEvents;
import com.challange.statemachine.domain.EmployeeCheckStates;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@EnableStateMachineFactory
@Configuration
//@EnableStateMachine(name = "employeeStateMachine")
public class StateMachineConfig extends StateMachineConfigurerAdapter<EmployeeCheckStates, EmployeeCheckEvents> {

	@Override
    public void configure(StateMachineConfigurationConfigurer<EmployeeCheckStates, EmployeeCheckEvents> config)
            throws Exception {
        
		StateMachineListenerAdapter<EmployeeCheckStates, EmployeeCheckEvents> adapter = new StateMachineListenerAdapter<EmployeeCheckStates, EmployeeCheckEvents>() {
			@Override
			public void stateChanged(State<EmployeeCheckStates, EmployeeCheckEvents> from, State<EmployeeCheckStates, EmployeeCheckEvents> to) {
		        //LOGGER.info(() -> String.format("Transitioned from %s to %s%n", from == null ? "none" : from.getId(), to.getId()));
		        System.out.println("state changed from " + to.getId());
		        log.info(String.format("State Changed from : %s, to: %s", from, to));
		        
		    }
		};
		
		config.withConfiguration().listener(adapter);
    }

	@Override
	public void configure(StateMachineStateConfigurer<EmployeeCheckStates, EmployeeCheckEvents> states)
			throws Exception {
//		states.withStates()
//		  .initial(EmployeeCheckStates.ADDED)
//		  .end(EmployeeCheckStates.ACTIVE)
//		  .states(EnumSet.allOf(EmployeeCheckStates.class));
		
		states.
			withStates()
			.initial(EmployeeCheckStates.ADDED)
			.fork(EmployeeCheckStates.IN_CHECK)
            .join(EmployeeCheckStates.JOIN)
            .state(EmployeeCheckStates.APPROVED)
            .end(EmployeeCheckStates.ACTIVE)
            .and()
            .withStates()
	            .parent(EmployeeCheckStates.IN_CHECK)
	            .initial(EmployeeCheckStates.SECURITY_CHECK_STARTED)
	            .end(EmployeeCheckStates.SECURITY_CHECK_FINISHED)
	        .and()
	        .withStates()
	            .parent(EmployeeCheckStates.IN_CHECK)
	            .initial(EmployeeCheckStates.WORK_PERMIT_CHECK_STARTED)
	            .end(EmployeeCheckStates.WORK_PERMIT_CHECK_FINISHED);
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<EmployeeCheckStates, EmployeeCheckEvents> transitions)
			throws Exception {
//		transitions.withExternal().source(EmployeeCheckStates.ADDED).target(EmployeeCheckStates.IN_CHECK)
//				.event(EmployeeCheckEvents.BEGIN_CHECK)
//				.and().withExternal()
//				.source(EmployeeCheckStates.IN_CHECK).target(EmployeeCheckStates.APPROVED)
//				.event(EmployeeCheckEvents.APPROVE)
//				.and().withExternal()
//				.source(EmployeeCheckStates.APPROVED).target(EmployeeCheckStates.IN_CHECK)
//				.event(EmployeeCheckEvents.UNAPPROVE)
//				.and().withExternal()
//				.source(EmployeeCheckStates.APPROVED).target(EmployeeCheckStates.ACTIVE)
//				.event(EmployeeCheckEvents.ACTIVAT);
		
		transitions.withExternal().source(EmployeeCheckStates.ADDED).target(EmployeeCheckStates.IN_CHECK)
			.event(EmployeeCheckEvents.BEGIN_CHECK)
			.and()
			.withExternal()
            	.source(EmployeeCheckStates.SECURITY_CHECK_STARTED).target(EmployeeCheckStates.SECURITY_CHECK_FINISHED).event(EmployeeCheckEvents.SECURITY_CHECK_FINISH)
            .and()
            .withExternal()
            	.source(EmployeeCheckStates.WORK_PERMIT_CHECK_STARTED).target(EmployeeCheckStates.WORK_PERMIT_PENDING_VERIFICATION).event(EmployeeCheckEvents.INITIAL_WORK_PERMIT_CHECK_FINISH)
            .and()
            .withExternal()
        		.source(EmployeeCheckStates.SECURITY_CHECK_FINISHED).target(EmployeeCheckStates.WORK_PERMIT_PENDING_VERIFICATION).event(EmployeeCheckEvents.INITIAL_WORK_PERMIT_CHECK_FINISH)
        	.and()
        	.withExternal()
    			.source(EmployeeCheckStates.WORK_PERMIT_PENDING_VERIFICATION).target(EmployeeCheckStates.SECURITY_CHECK_FINISHED).event(EmployeeCheckEvents.SECURITY_CHECK_FINISH)
    		.and()
            	.withExternal()
        		.source(EmployeeCheckStates.WORK_PERMIT_CHECK_FINISHED).target(EmployeeCheckStates.SECURITY_CHECK_FINISHED).event(EmployeeCheckEvents.SECURITY_CHECK_FINISH)
    		.and()
            .withExternal()
            	.source(EmployeeCheckStates.WORK_PERMIT_PENDING_VERIFICATION).target(EmployeeCheckStates.WORK_PERMIT_CHECK_FINISHED).event(EmployeeCheckEvents.WORK_PERMIT_CHECK_FINISH)
            .and()
            .withExternal()
                .source(EmployeeCheckStates.JOIN).target(EmployeeCheckStates.APPROVED)
             .and()
            .withExternal()
                .source(EmployeeCheckStates.APPROVED).target(EmployeeCheckStates.ACTIVE).event(EmployeeCheckEvents.ACTIVAT)
            .and()
            .withFork()
                .source(EmployeeCheckStates.IN_CHECK)
                .target(EmployeeCheckStates.SECURITY_CHECK_STARTED)
                .target(EmployeeCheckStates.WORK_PERMIT_CHECK_STARTED)
            .and()
            .withJoin()
                .source(EmployeeCheckStates.SECURITY_CHECK_FINISHED)
                .source(EmployeeCheckStates.WORK_PERMIT_CHECK_FINISHED)
                .target(EmployeeCheckStates.JOIN);
            ;

	}

}
