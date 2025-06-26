package com.param.april_project_2025.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.param.april_project_2025.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}