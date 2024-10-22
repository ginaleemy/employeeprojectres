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
import com.employee.rest.api.jpa.ProjectRepositoryInterface;
import com.employee.rest.api.model.Project;

import jakarta.validation.Valid;

@RestController
public class ProjectController {

	private ProjectRepositoryInterface service;

	public ProjectController(ProjectRepositoryInterface service) {
		this.service = service;
	}

	@GetMapping("/api/projects")
	public List<Project> retrieveAllProjects() {
		return service.findAll();
	}

	@GetMapping("/api/projects/{id}")
	public EntityModel<Project> retrieveProj(@PathVariable int id) {
		Optional<Project> project = service.findById(id);
		if (project.isEmpty())
			throw new RecNotFoundException("Record not exist, System not found proejct id:"+id );

		EntityModel<Project> entityModel = EntityModel.of(project.get());

		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllProjects());
		entityModel.add(link.withRel("all-projects"));

		return entityModel;
	}

	@DeleteMapping("/api/projects/{id}")
	public ResponseEntity<String> deleteProj(@PathVariable int id) {
		Optional<Project> proj = null;
		try {
			proj = service.findById(id);
			if (proj.isEmpty())
				throw new RecNotFoundException("Record not exist, System not found proejct id:"+id );
			
			service.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			// Handle foreign key constraint violation
			System.out.println("Foreign key constraint violation: " + e.getMessage());
			throw new NotAbleDeleteException("Found dependency, System not able delete project id:" + id);
		} 
		

		//return ResponseEntity.noContent().build();
		return ResponseEntity.ok("Operation completed successfully");
	}

	@PostMapping("/api/projects")
	public ResponseEntity<Project> createProject(@Valid @RequestBody Project proj) {
		Project savedProj = service.save(proj);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedProj.getId())
				.toUri();
		//return ResponseEntity.created(location).build();
		return ResponseEntity.ok().header("Location", location.toString()).build();  
	}

}
