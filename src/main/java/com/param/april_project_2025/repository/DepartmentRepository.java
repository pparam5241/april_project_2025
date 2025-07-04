package com.param.april_project_2025.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.param.april_project_2025.entity.Department;
import com.param.april_project_2025.models.Departments;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

//	@Query(value = "select * from department where name= :departmentName", nativeQuery = true)
//	Optional<Department> getDepartmentByName(Departments departmentName);

	Optional<Department> findByName(Departments name);
}