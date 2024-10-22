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
import com.employee.rest.api.jpa.DeptRepositoryInterface;
import com.employee.rest.api.jpa.EmployeeRepositoryInterface;
import com.employee.rest.api.model.Dept;
import com.employee.rest.api.model.Employee;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {

	private EmployeeRepositoryInterface service;
	
	private DeptRepositoryInterface departmentService;

	public EmployeeController(EmployeeRepositoryInterface service
			, DeptRepositoryInterface departmentService) {
		this.service = service;
		this.departmentService = departmentService;
	}

	@GetMapping("/api/employees")
	public List<Employee> retrieveAllEmps() {
		return service.findAll();
	}

	@GetMapping("/api/employees/{id}")
	public EntityModel<Employee> retrieveEmp(@PathVariable int id) {
		Optional<Employee> empt = service.findById(id);

		if (empt.isEmpty())
			throw new RecNotFoundException("Record not exist, System not found employee id:" + id);

		EntityModel<Employee> entityModel = EntityModel.of(empt.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllEmps());
		entityModel.add(link.withRel("all-employee"));

		return entityModel;
	}

	@DeleteMapping("/api/employees/{id}")
	public ResponseEntity<String>  deleteEmp(@PathVariable int id) {
		Optional<Employee> empt = null;
		try {
			empt = service.findById(id);
			if (empt.isEmpty())
				throw new RecNotFoundException("Record not exist, System not found employee id:" + id);
			service.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			// Handle foreign key constraint violation
			System.out.println("Foreign key constraint violation: " + e.getMessage());

			throw new NotAbleDeleteException("Found dependency, System not able delete Employee id:" + id);
		}
		//return ResponseEntity.noContent().build();
		return ResponseEntity.ok("Operation completed successfully");
	}

	@PostMapping("/api/employees")
	public ResponseEntity<Employee> createEmp(@Valid @RequestBody Employee emp) {
		
		Integer maxCode = service.findMaxEmployeeCode();
		Integer nextCode = (maxCode != null) ? maxCode + 1 : 1;
		String employeeCode = String.format("EMP%04d", nextCode);
		emp.setEmployeeCode(employeeCode);
		
		//Find Department
		if (emp.getDepartmentId() == null) {
			throw new RecNotFoundException("Department id is empty." );
		}
		Optional<Dept> dept = departmentService.findById(emp.getDepartmentId().intValue());
		if (dept.isEmpty())
			throw new RecNotFoundException("Record not exist, System not found department id:"+emp.getDepartmentId() );
		emp.setDepartment(dept.get());
		
		Employee tpEmp = service.save(emp);
		Optional<Employee> newEmpt = service.findById(tpEmp.getId());
		

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEmpt.get().getId())
				.toUri();

		//return ResponseEntity.created(location).build();
		return ResponseEntity.ok().header("Location", location.toString()).build(); 
	}

}
