package com.employee.rest.api.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.rest.api.model.EmployeeProject;

import jakarta.transaction.Transactional;



public interface EmployeeProjectRepositoryInterface extends JpaRepository<EmployeeProject, Integer> {
	@Transactional
	public Optional<EmployeeProject> findById(Integer id);
	
	
}
