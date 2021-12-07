package com.cg.springmongodb.model.exception;

import java.text.MessageFormat;

public class EmployeeNotFoundException extends RuntimeException{

	public EmployeeNotFoundException(int id) {
		super(MessageFormat.format("Could not find employee with Id : ", id));
	}
}
