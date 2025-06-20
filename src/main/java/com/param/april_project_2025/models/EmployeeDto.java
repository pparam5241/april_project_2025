package com.param.april_project_2025.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class EmployeeDto {
	private Integer id;
	private String name;
	private Double salary;
	
	@Enumerated(EnumType.STRING)
	private Departments department;
}