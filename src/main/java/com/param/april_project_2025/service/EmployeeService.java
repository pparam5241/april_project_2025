package com.param.april_project_2025.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.param.april_project_2025.clients.SalaryClient;
import com.param.april_project_2025.dao.DepartmentDao;
import com.param.april_project_2025.entity.Employee;
import com.param.april_project_2025.exception.EmployeeNotFoundException;
import com.param.april_project_2025.models.Departments;
import com.param.april_project_2025.models.EmployeeCreateDto;
import com.param.april_project_2025.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

//	// Field Injection
//	@Autowired
//	private EmployeeRepository employeeRepository;
//
//	// Constructor Injection
//	@Autowired
//	public EmployeeService(EmployeeRepository employeeRepository) {
//		this.employeeRepository = employeeRepository;
//	}
//
//	// Setter Injection
//	@Autowired
//	public void setEmployeeRepository(EmployeeRepository repository) {
//		this.employeeRepository = repository;
//	}

	private final EmployeeRepository employeeRepository;
	private final DepartmentDao departmentDao;
	private final SalaryClient salaryClient;

	// Whenever any request coming for creation of employee, I'll be using this
	// function
	public Employee createEmployee(EmployeeCreateDto dto) {
		/*
		 * // If I'm not going with @Builder process Employee emp = new Employee();
		 * emp.setName(dto.getName()); emp.setSalary(dto.getSalary());
		 */

		var dept = departmentDao.getByDepartmentName(dto.getDepartment());
		// I'm using Builder here for Object Creation
		Employee employee = Employee.builder().salary(dto.getSalary()).name(dto.getName()).department(dept).build();
		// For saving this object, We'd be needing Repository which will help me to
		// communicate with database

		employeeRepository.save(employee);
		log.info("New Employee Created with ID: {}", employee.getId());
		return employee;
	}

	public Page<Employee> findAllEmployees(int page, int pageSize, String search) {
		Pageable pageable = PageRequest.of(page, pageSize);
		Page<Employee> employees = employeeRepository.findAllByNameLike("%" + search + "%", pageable);
		return employees;
	}

	public Employee updateEmployee(Long empId, EmployeeCreateDto dto) {
		// We have to check weather provided employee is there in Database or not
		Optional<Employee> opt = employeeRepository.findById(empId);
		Employee employee = opt
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + empId));
		employee.setName(dto.getName());
		employee.setSalary(dto.getSalary());
		return employeeRepository.save(employee);
	}

	public void deleteEmployeeById(Long id) {
		// I should go with Object Delete -> If entry is not available then It will
		// throw exception to us

		// I should go with deleteById -> If entry is not there, Then we won't have any
		// exception and it will continue execution
		employeeRepository.deleteEmployeeById(id);
//		employeeRepository.delete(entity); - If we have employee object instead of ID then we can use this method
	}

	public Employee getEmployeeById(Long id) {
//		Optional<Employee> opt = employeeRepository.findById(id);
//		Employee emp = opt.orElseThrow(() -> new EmployeeNotFoundException("<Message>"));
//		return emp;
		Employee employee = employeeRepository.getEmployeeById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
		employee.setSalaries(salaryClient.fetchSalaryByEmployee(id));
		return employee;
	}

	public List<Employee> getAllEmployeesByDepartmentName(Departments name) {
		return employeeRepository.findByDepartment_Name(name);
	}

	public void asyncExample(Integer waitTime) {
		callAsyncMethod(waitTime);
	}

	@Async
	private void callAsyncMethod(Integer waitTime) {
		log.info("Async Method Execution Started");
		for (int i = 0; i < waitTime / 1000; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i + 1);
		}
		log.info("Async Method Execution Completed");
	}
}