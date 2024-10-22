package com.employee.rest.api.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.rest.api.model.Dept;



public interface DeptRepositoryInterface extends JpaRepository<Dept, Integer> {
	Dept findByName(String name);
	
	
}
