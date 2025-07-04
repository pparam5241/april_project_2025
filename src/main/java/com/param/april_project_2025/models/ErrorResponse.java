package com.param.april_project_2025.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
	private String message;
	private int statusCode;
}