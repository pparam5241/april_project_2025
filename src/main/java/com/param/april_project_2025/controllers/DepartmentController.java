package com.param.april_project_2025.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.param.april_project_2025.entity.Department;
import com.param.april_project_2025.models.DepartmentCreateDto;
import com.param.april_project_2025.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/department")
@Slf4j
@RequiredArgsConstructor
public class DepartmentController {
	private final DepartmentService departmentService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Department> createDepartment(@RequestBody DepartmentCreateDto dto) {
		return ResponseEntity.ok(departmentService.createDepartment(dto));
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping
	public ResponseEntity<Page<Department>> fetchAllDepartments(@RequestParam int page, @RequestParam int pageSize) {
		return ResponseEntity.ok(departmentService.fetchAllEmployees(page, pageSize));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable Integer id, @RequestBody DepartmentCreateDto dto) {
		return ResponseEntity.ok(departmentService.updateDepartment(id, dto));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteDepartmentById(@PathVariable Integer id) {
		departmentService.deleteDepartmentById(id);
		Map<String, Object> map = new HashMap<>();
		map.put("statusCode", 200);
		map.put("message", "Department Deleted Successfully!");
		return ResponseEntity.ok(map);
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Integer id) {
		return ResponseEntity.ok(departmentService.getDepartmentByid(id));
	}
}