package com.param.april_project_2025.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SalaryClient {
	private static String FETCH_SALARY_URL = "http://localhost:8081/salary?employeeId=";
	private RestTemplate restTemplate = new RestTemplate();

	public Object fetchSalaryByEmployee(Long employeeId) {
		String url = FETCH_SALARY_URL + employeeId;
		return restTemplate.getForEntity(url, Object.class).getBody();
	}
}