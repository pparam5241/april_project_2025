package com.param.april_project_2025.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.param.april_project_2025.models.EmployeeDto;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	private static Integer nextEmployeeId = 1;
	private static Map<Integer, EmployeeDto> employees = new HashMap<>();

	@PostMapping
	public ResponseEntity<String> createEmployee(@RequestBody EmployeeDto employeeDto) {
		employeeDto.setId(nextEmployeeId);
		nextEmployeeId++;
		employees.put(employeeDto.getId(), employeeDto);
		return ResponseEntity.ok("Employee Created Successfully with ID: " + employeeDto.getId());
	}

	@GetMapping
	public ResponseEntity<Collection<EmployeeDto>> fetchAllEmployees() {
		return ResponseEntity.ok(employees.values());
	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "employeeId") Integer id) {
		return ResponseEntity.ok(employees.get(id));
	}

	@PutMapping("/{employeeId}")
	public ResponseEntity<String> updateEmployeeById(@PathVariable Integer employeeId, @RequestBody EmployeeDto dto) {
		employees.put(employeeId, dto);
		return ResponseEntity.ok("Employee Details Updated Successfully!");
	}
	
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer employeeId) {
		employees.remove(employeeId);
		return ResponseEntity.ok("Employee Deleted Successfully!");
	}
}