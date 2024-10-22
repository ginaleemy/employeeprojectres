package com.employee.rest.webservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.rest.webservices.model.Dept;



public interface DeptRepositoryInterface extends JpaRepository<Dept, Integer> {
	Dept findByName(String name);
	
	
}
