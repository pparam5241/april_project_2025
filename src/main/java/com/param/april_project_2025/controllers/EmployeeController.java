package com.param.april_project_2025.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.param.april_project_2025.entity.Employee;
import com.param.april_project_2025.models.Departments;
import com.param.april_project_2025.models.EmployeeCreateDto;
import com.param.april_project_2025.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v2/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

	private final EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody @Valid EmployeeCreateDto dto) {
		log.info("Employee Create Request Received with DTO: {}", dto);
		Employee emp = employeeService.createEmployee(dto);
		return ResponseEntity.ok(emp);
	}

	@GetMapping
	public ResponseEntity<Page<Employee>> fetchAllEmployees(@RequestParam(name = "page") int page,
			@RequestParam int pageSize, @RequestParam String search) {
		return ResponseEntity.ok(employeeService.findAllEmployees(page, pageSize, search));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(name = "id") Long empId,
			@RequestBody @Valid EmployeeCreateDto dto) {
		return ResponseEntity.ok(employeeService.updateEmployee(empId, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
		employeeService.deleteEmployeeById(id);
		return ResponseEntity.ok("Employee Deleted Successfully!");
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		return ResponseEntity.ok(employeeService.getEmployeeById(id));
	}

	@GetMapping("/department/{name}")
	public ResponseEntity<List<Employee>> getAllEmployeesByDepartmentName(@PathVariable Departments name) {
		return ResponseEntity.ok(employeeService.getAllEmployeesByDepartmentName(name));
	}

	@PostMapping("/async/{waitTime}")
	public ResponseEntity<HttpStatus> asyncMethod(@PathVariable Integer waitTime) {
		CompletableFuture.runAsync(new Runnable() {
			public void run() {
				employeeService.asyncExample(waitTime);
			}
		});
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/async/v2/{waitTime}")
	public ResponseEntity<HttpStatus> asyncMethodByUsingAnnotation(@PathVariable Integer waitTime) {
		employeeService.asyncExample(waitTime);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}