package com.param.april_project_2025.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "employee_details")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "employee_id")
	private Long id;

	private String name;

	private Double salary;

	@ManyToOne
	@JoinColumn(name = "dept_id", nullable = false)
	private Department department;
}