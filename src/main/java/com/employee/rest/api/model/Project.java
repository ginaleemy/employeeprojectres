package com.employee.rest.api.model;

import com.employee.rest.api.validation.SizeRange;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates the ID
	private Integer id;

	
	@SizeRange(min = 1, max = 100, message = "Project name must be between 1 and 100 characters")
	@Column(name = "name", nullable = false, length = 100) // Maps the name column
	private String name;

	
	@SizeRange(min = 1, max = 2, message = "Project status must be exactly 2 characters")
	@Column(name = "project_status", nullable = false, length = 2) // Maps the project_status column
	private String projectStatus;
	
	 // Default constructor
    public Project() {
    }

    // Parameterized constructor
    public Project(String name, String projectStatus) {
        this.name = name;
        this.projectStatus = projectStatus;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", projectStatus=" + projectStatus + "]";
	}

	
}
