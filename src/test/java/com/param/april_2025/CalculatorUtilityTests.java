package com.param.april_2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.param.april_project_2025.April2025SpringBootProjectApplication;
import com.param.april_project_2025.utilities.CalculatorUtility;

@SpringBootTest(classes = April2025SpringBootProjectApplication.class)
public class CalculatorUtilityTests {

	@Test
	void testAdd1() {
		Assertions.assertEquals(30, CalculatorUtility.add(10, 20));
	}

	@Test
	void testAddBigNumber() {
		Assertions.assertEquals(1500, CalculatorUtility.add(1000, 500));
	}

	@Test
	void testNegativeAdd() {
		Assertions.assertNotEquals(5000, CalculatorUtility.add(2000, 500));
	}

	@Test
	void testSub() {
		Assertions.assertEquals(-10, CalculatorUtility.sub(10, 20));
	}

	@Test
	void testSubBigNumber() {
		Assertions.assertEquals(500, CalculatorUtility.sub(1000, 500));
	}

	@Test
	void testNegativeSub() {
		Assertions.assertNotEquals(5000, CalculatorUtility.sub(2000, 500));
	}
}