package com.param.april_project_2025.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.param.april_project_2025.dao.DepartmentDao;
import com.param.april_project_2025.entity.Department;
import com.param.april_project_2025.models.DepartmentCreateDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentService {
	private final DepartmentDao dao;

	public Department createDepartment(DepartmentCreateDto dto) {
		Department department = Department.builder().name(dto.getName()).build();
		return dao.saveAndGet(department);
	}

	public Page<Department> fetchAllEmployees(int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return dao.findAll(pageable);
	}

	public Department updateDepartment(Integer id, DepartmentCreateDto dto) {
		var dept = dao.getDepartmentById(id);
		dept.setName(dto.getName());
		return dao.saveAndGet(dept);
	}

	public void deleteDepartmentById(Integer id) {
		dao.deleteById(id);
	}

	public Department getDepartmentByid(Integer id) {
		return dao.getDepartmentById(id);
	}
}