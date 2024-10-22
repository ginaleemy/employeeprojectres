package com.employee.rest.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.employee.rest.api.exception.NotAbleDeleteException;
import com.employee.rest.api.exception.RecNotFoundException;
import com.employee.rest.api.jpa.EmployeeProjectRepositoryInterface;
import com.employee.rest.api.jpa.EmployeeRepositoryInterface;
import com.employee.rest.api.jpa.ProjectRepositoryInterface;
import com.employee.rest.api.model.Employee;
import com.employee.rest.api.model.EmployeeProject;
import com.employee.rest.api.model.Project;

import jakarta.validation.Valid;

@RestController
public class EmployeeSystemController {

	private EmployeeProjectRepositoryInterface service;
	
	private EmployeeRepositoryInterface employeeService;
	
	private ProjectRepositoryInterface projectService;

	public EmployeeSystemController(EmployeeProjectRepositoryInterface service
			,EmployeeRepositoryInterface employeeService
			,ProjectRepositoryInterface projectService) {
		this.service = service;
		this.employeeService = employeeService;
		this.projectService = projectService;
	}

	@GetMapping("/api/employeeprojects")
	public List<EmployeeProject> retrieveAllProjects() {
		return service.findAll();
	}

	@GetMapping("/api/employeeprojects/{id}")
	public EntityModel<EmployeeProject> retrieveEmployeeProject(@PathVariable int id) {
		Optional<EmployeeProject> empt = service.findById(id);
		
		if (empt.isEmpty())
			throw new RecNotFoundException("Record not exist, System not found employee project id:"+id );
		
		EntityModel<EmployeeProject> entityModel = EntityModel.of(empt.get());
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllProjects());
		entityModel.add(link.withRel("all-employee-project"));
		
		return entityModel;

	}

	@DeleteMapping("/api/employeeprojects/{id}")
	public ResponseEntity<String> deleteEmployeeProject(@PathVariable int id) {
		Optional<EmployeeProject> proj = null;
		try {
			proj = service.findById(id);
			if (proj.isEmpty())
				throw new RecNotFoundException("Record not exist, System not found employee project id:"+id );
			service.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			// Handle foreign key constraint violation
			System.out.println("Foreign key constraint violation: " + e.getMessage());
			throw new NotAbleDeleteException("Found dependency, System not able delete employee project id:" + id);
		} 
		//return ResponseEntity.noContent().build();
		return ResponseEntity.ok("Operation completed successfully");
	}


	@PostMapping("/api/employeeprojects")
	public ResponseEntity<EmployeeProject> createEmployeeProject(@Valid @RequestBody EmployeeProject proj) {
		
		System.out.println("----> proj.tostring  = "+proj.toString());
		System.out.println("----> proj.getProjectId()  = "+proj.getProjectId());
		System.out.println("----> proj.getEmployeeCode()  = "+proj.getEmployeeCode());
		
		
		Optional<Project> optProject = projectService.findById(proj.getProjectId());
		if (optProject.isEmpty())
			throw new RecNotFoundException("Record not exist, System not found project id:"+proj.getProjectId() );
		
		
		
		Optional<Employee> optEmp = employeeService.findByEmployeeCode(proj.getEmployeeCode());
		if (optEmp.isEmpty())
			throw new RecNotFoundException("Record not exist, System not found Employee Code:"+proj.getEmployeeCode());
		
		
		EmployeeProject newEmpProj = service.save(new EmployeeProject(optProject.get(), optEmp.get()));
		
		System.out.println("----> optEmp.get() = "+optEmp.get());
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEmpProj.getId())
				.toUri();

		//return ResponseEntity.created(location).build();
		return ResponseEntity.ok().header("Location", location.toString()).build(); 
	}

}
