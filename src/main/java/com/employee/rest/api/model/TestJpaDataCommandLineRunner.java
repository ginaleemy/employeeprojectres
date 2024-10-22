package com.employee.rest.api.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.employee.rest.api.exception.RecNotFoundException;
import com.employee.rest.api.jpa.DeptRepositoryInterface;
import com.employee.rest.api.jpa.EmployeeProjectRepositoryInterface;
import com.employee.rest.api.jpa.EmployeeRepositoryInterface;
import com.employee.rest.api.jpa.ProjectRepositoryInterface;




//@Component
public class TestJpaDataCommandLineRunner implements CommandLineRunner{
	
	@Autowired
	private DeptRepositoryInterface repositoryDept;
	
	@Autowired
	private EmployeeRepositoryInterface repositoryEmp;

	@Autowired
	private ProjectRepositoryInterface repositoryProj;
	
	@Autowired
	private EmployeeProjectRepositoryInterface repositorySproj;
	
    	@Override
        public void run(String... args) throws Exception {
//		public void run(String args) throws Exception {
		// TODO Auto-generated method stub
		Dept dept = repositoryDept.save(new Dept("Parcel"));  //1
		Project newProj = repositoryProj.save(new Project("MRT PROJECT","A")); //2
		
	  //	Integer maxCode = repositoryEmp.findMaxEmployeeCode();
      //  Integer nextCode = (maxCode != null) ? maxCode + 1 : 1;
      //  String employeeCode = String.format("EMP%04d", nextCode);
		Employee tpEmp = repositoryEmp.save(new Employee("Lim Ah Kow", "Assistant", dept, "Emp001"));  //3		
		
		Integer newEmployeeId = tpEmp.getId();
		Optional<Employee> optEmp = repositoryEmp.findById(newEmployeeId);
		Employee newEmp = optEmp.orElseThrow(() ->  new RecNotFoundException("Employee with ID " + newEmployeeId + " not found"));
		
		
		
		EmployeeProject newEmpProj = repositorySproj.save(new EmployeeProject(newProj, newEmp));
		
		
		System.out.println(newEmpProj);
		
		//System.out.println("------------Project------------------------------");
		//System.out.println(repositoryProj.findAll());
		//System.out.println(repositoryProj.count());
		
		//System.out.println("------------Department------------------------------");
		//System.out.println(repositoryDept.findAll());
		//System.out.println(repositoryDept.count());
		
		
		
	}
}
