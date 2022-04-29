package com.challange.statemachine.domain;

public enum EmployeeCheckEvents {
	ADDED, 
	BEGIN_CHECK,
	SECURITY_CHECK_START,
	SECURITY_CHECK_FINISH,
	WORK_PERMIT_CHECK_START,
	INITIAL_WORK_PERMIT_CHECK_FINISH,
    WORK_PERMIT_CHECK_FINISH,
	APPROVE, 
	UNAPPROVE, 
	ACTIVAT
}
