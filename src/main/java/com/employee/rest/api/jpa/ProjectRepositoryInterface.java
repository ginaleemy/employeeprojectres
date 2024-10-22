package com.employee.rest.webservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.rest.webservices.model.Project;



public interface ProjectRepositoryInterface extends JpaRepository<Project, Integer> {
	Project findByName(String name);
}
