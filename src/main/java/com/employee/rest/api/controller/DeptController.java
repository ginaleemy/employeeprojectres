package com.employee.rest.webservices.controller;

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

import com.employee.rest.webservices.exception.NotAbleDeleteException;
import com.employee.rest.webservices.exception.RecNotFoundException;
import com.employee.rest.webservices.jpa.DeptRepositoryInterface;
import com.employee.rest.webservices.model.Dept;

import jakarta.validation.Valid;

@RestController
public class DeptController {

	private DeptRepositoryInterface service;

	public DeptController(DeptRepositoryInterface service) {
		this.service = service;
	}

	@GetMapping("/dept")
	public List<Dept> retrieveAllDepts() {
		return service.findAll();
	}

	@GetMapping("/dept/{id}")
	public EntityModel<Dept> retrieveDept(@PathVariable int id) {
		Optional<Dept> dept = service.findById(id);
		
		if (dept.isEmpty())
			throw new RecNotFoundException("Record not exist, System not found department id:"+id );
			 
		EntityModel<Dept> entityModel = EntityModel.of(dept.get());
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllDepts());
		entityModel.add(link.withRel("all-depts"));
		
		return entityModel;
	}
	
	
	@DeleteMapping("/dept/{id}")
	public ResponseEntity<String> deleteDept(@PathVariable int id) {
		Optional<Dept> dept = null;
		try {
			dept = service.findById(id);
			if (dept.isEmpty())
				throw new RecNotFoundException("Record not exist, System not found department id:"+id );
			
			service.deleteById(id);
		} catch (DataIntegrityViolationException e) {
		    // Handle foreign key constraint violation
		    System.out.println("Foreign key constraint violation: " + e.getMessage());
		    
		    throw new NotAbleDeleteException("Found dependency, System not able delete department id:"+id );
		} 
		//eturn ResponseEntity.noContent().build();
		return ResponseEntity.ok("Operation completed successfully");
	}

	@PostMapping("/dept")
	public ResponseEntity<Dept> createDept(@Valid @RequestBody Dept dept) {
		
		Dept savedDept = service.save(dept);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedDept.getId())
						.toUri();   
		
		//return ResponseEntity.created(location).build();
		return ResponseEntity.ok().header("Location", location.toString()).build();  
	}
	
	
}
