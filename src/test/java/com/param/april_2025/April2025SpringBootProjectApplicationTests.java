package com.param.april_2025;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.param.april_project_2025.April2025SpringBootProjectApplication;
import com.param.april_project_2025.controllers.DepartmentController;
import com.param.april_project_2025.controllers.EmployeeController;
import com.param.april_project_2025.controllers.EmployeeUsingHashMapController;
import com.param.april_project_2025.controllers.PublicController;

@SpringBootTest(classes = April2025SpringBootProjectApplication.class)
class April2025SpringBootProjectApplicationTests {

	@Autowired
	private DepartmentController departmentController;
	@Autowired
	private EmployeeController employeeController;
	@Autowired
	private PublicController publicController;
	@Autowired
	private EmployeeUsingHashMapController employeeUsingHashMapController;

	@Test
	void contextLoads() {
		Assertions.assertThat(departmentController).isNotNull();
		Assertions.assertThat(employeeController).isNotNull();
		Assertions.assertThat(publicController).isNotNull();
		Assertions.assertThat(employeeUsingHashMapController).isNotNull();
	}
}