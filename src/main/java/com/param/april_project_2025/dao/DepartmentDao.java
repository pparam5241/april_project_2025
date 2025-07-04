package com.param.april_project_2025.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.param.april_project_2025.entity.Department;
import com.param.april_project_2025.exception.DepartmentNotFoundException;
import com.param.april_project_2025.models.Departments;
import com.param.april_project_2025.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DepartmentDao {
	private final DepartmentRepository repository;

	public Department getDepartmentById(Integer id) {
		Optional<Department> opt = repository.findById(id);
		if (opt.isEmpty()) {
			throw new DepartmentNotFoundException("Department not found with ID: " + id);
		}
		return opt.get();
	}

	public Department getByDepartmentName(Departments departmentName) {
		Optional<Department> opt = repository.findByName(departmentName);
		if (opt.isEmpty()) {
			throw new DepartmentNotFoundException("Department not found with Name: " + departmentName);
		}
		return opt.get();
	}

	public Department saveAndGet(Department department) {
		return repository.save(department);
	}

	public Page<Department> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
}