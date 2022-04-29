package com.challange.statemachine.domain;

public enum EmployeeCheckStates {
	ADDED, 
    IN_CHECK, 
    APPROVED, 
    ACTIVE,
    JOIN,
	SECURITY_CHECK_STARTED,
    SECURITY_CHECK_FINISHED, 
    WORK_PERMIT_CHECK_STARTED,
    WORK_PERMIT_PENDING_VERIFICATION,
    WORK_PERMIT_CHECK_FINISHED;
}
