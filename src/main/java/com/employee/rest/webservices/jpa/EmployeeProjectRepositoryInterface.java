package com.employee.rest.webservices.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.rest.webservices.model.EmployeeProject;

import jakarta.transaction.Transactional;



public interface EmployeeProjectRepositoryInterface extends JpaRepository<EmployeeProject, Integer> {
	@Transactional
	public Optional<EmployeeProject> findById(Integer id);
	
	
}
