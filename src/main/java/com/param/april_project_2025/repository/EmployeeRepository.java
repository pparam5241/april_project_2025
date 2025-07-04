package com.param.april_project_2025.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.param.april_project_2025.entity.Employee;
import com.param.april_project_2025.models.Departments;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	// I want to provide filters on which columns? -> name
	Page<Employee> findAllByNameLike(String name, Pageable pageable);

//	@Query("delete from Employee where id = :id")
	@Modifying
	@Transactional
	@Query(value = "delete from employee where id = :id", nativeQuery = true)
	void deleteEmployeeById(Long id);

	@Query("from Employee where id = :id")
	Optional<Employee> getEmployeeById(Long id);

	List<Employee> findByDepartment_Name(Departments name);
}