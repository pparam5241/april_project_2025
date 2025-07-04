package com.param.april_project_2025.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepartmentCreateDto {

	@NotNull(message = "Department cannot be null or empty.")
	private Departments name;
}