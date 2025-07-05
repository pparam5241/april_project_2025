package com.param.april_project_2025.models;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentCreateDto {

	@NotNull(message = "Department cannot be null or empty.")
	private Departments name;
}