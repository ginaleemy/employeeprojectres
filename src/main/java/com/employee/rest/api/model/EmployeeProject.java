package com.employee.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(
	    name = "employee_project",
	    uniqueConstraints = @UniqueConstraint(columnNames = {"project_id", "employee_code"})
	)
@JsonIgnoreProperties({"employee", "project"}) 
public class EmployeeProject {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates the ID
	private Integer id;

	
	@ManyToOne
	@JoinColumn(name = "project_id"
	, referencedColumnName = "id"
	, foreignKey = @ForeignKey(name = "FK_EMPPROJ_PROJ") 
	)
	private Project project;

	
	@ManyToOne
	@NotBlank(message = "Employee code is required")
	@Size(min = 1, max = 100, message = "Project name must be between 1 and 100 characters")
	@JoinColumn(name = "employee_code"
	, referencedColumnName = "employee_code"
	, nullable = true
	,foreignKey = @ForeignKey(name = "FK_EMPPROJ_EMPCODE") 
	)
	private Employee employee;
	
	@Transient
	private Integer projectId;
	
	@Transient
	private String employeeCode;
	
	@PostLoad
    public void postLoad() {
        if (this.project != null) {
            this.projectId = this.project.getId();
        }
        if (this.employee != null) {
            this.employeeCode = this.employee.getEmployeeCode();
        }
    }

	// Default constructor
	public EmployeeProject() {
	}

	public EmployeeProject(Project project, Employee employee) {
		super();
		this.project = project;
		this.employee = employee;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	@Override
	public String toString() {
		return "EmployeeProject [id=" + id + ", project=" + project + ", employee=" + employee + ", projectId="
				+ projectId + ", employeeCode=" + employeeCode + "]";
	}


}
