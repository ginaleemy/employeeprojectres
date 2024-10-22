package com.employee.rest.api.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.rest.api.model.Employee;

import jakarta.transaction.Transactional;



public interface EmployeeRepositoryInterface extends JpaRepository<Employee, Integer> {
	List<Employee> findByName(String name);
	
	@Query("SELECT MAX(CAST(SUBSTRING(e.employeeCode, 4) AS int)) FROM Employee e")
	Integer findMaxEmployeeCode();
	
	 // Find employee by employeeCode
    Optional<Employee> findByEmployeeCode(String employeeCode);
    
    @Transactional
	public Optional<Employee> findById(Integer id);
}
