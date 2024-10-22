package com.employee.rest.api.model;

import com.employee.rest.api.validation.SizeRange;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "dept")
public class Dept {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	
	@SizeRange(min = 2, max = 100, message = "Department name should have between 2 and 100 characters") // Custom size 	// validation
	private String name;
	

	public Dept() {
	}
	
	public Dept(String name) {
		super();
		this.name = name;
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

	@Override
	public String toString() {
		return "Dept [id=" + id + ", name=" + name + "]";
	}
}
