package com.employee.rest.api.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.rest.api.model.Project;



public interface ProjectRepositoryInterface extends JpaRepository<Project, Integer> {
	Project findByName(String name);
}
