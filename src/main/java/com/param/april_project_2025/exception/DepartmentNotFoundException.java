package com.param.april_project_2025.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DepartmentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7096025230837380966L;

	public DepartmentNotFoundException(String message) {
		super(message);
	}
}