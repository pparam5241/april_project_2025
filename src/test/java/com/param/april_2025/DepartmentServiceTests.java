package com.param.april_2025;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.param.april_project_2025.April2025SpringBootProjectApplication;
import com.param.april_project_2025.entity.Department;
import com.param.april_project_2025.exception.DepartmentNotFoundException;
import com.param.april_project_2025.models.DepartmentCreateDto;
import com.param.april_project_2025.models.Departments;
import com.param.april_project_2025.service.DepartmentService;

@SpringBootTest(classes = April2025SpringBootProjectApplication.class)
@AutoConfigureTestDatabase(replace = Replace.ANY)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class DepartmentServiceTests {

	@Autowired
	private DepartmentService service;

	private static Integer departmentId;

	@Test
	@Order(1)
	void testCreateDepartment() {
		Department dept = null;
		try {
			dept = service.getDepartmentByName(Departments.ROR);
		} catch (DepartmentNotFoundException e) {
			// continue execution
		}

		if (dept == null) {
			DepartmentCreateDto dto = DepartmentCreateDto.builder().name(Departments.ROR).build();
			dept = service.createDepartment(dto);
		}
		departmentId = dept.getId();
		assertThat(dept).isNotNull();
		assertThat(dept.getId()).isNotNull();
	}

	@Test
	@Order(2)
	void testCreatedDepartmentName() {
		Department dept = service.getDepartmentByid(departmentId);
		assertEquals(Departments.ROR, dept.getName());
	}
}