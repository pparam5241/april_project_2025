package com.param.april_project_2025.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeCreateDto {

	@NotBlank(message = "Employee name cannot be null or empty.")
	private String name;

	@NotNull(message = "Employee salary cannot be null or empty.")
	private Double salary;

	@NotNull(message = "Department name cannot be null or empty.")
	private Departments department;
}