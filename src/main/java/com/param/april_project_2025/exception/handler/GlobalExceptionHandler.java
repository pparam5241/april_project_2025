package com.param.april_project_2025.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.param.april_project_2025.exception.DepartmentNotFoundException;
import com.param.april_project_2025.models.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = DepartmentNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse departmentNotFoundHandler(DepartmentNotFoundException e) {
		return ErrorResponse.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(e.getMessage()).build();
	}
}