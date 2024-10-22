package com.employee.rest.api.model;

import com.employee.rest.api.validation.NoSpaces;
import com.employee.rest.api.validation.SizeRange;
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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
    name = "employee", 
    uniqueConstraints = {
        @UniqueConstraint(name = "UK_EMPLOYEE_CODE", columnNames = "employee_code") // Custom unique constraint name
    }
)
@JsonIgnoreProperties({"department"}) 
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "Name is required")
	@SizeRange(min = 2, max = 100, message = "Name should have between 2 and 100 characters") // Custom size validation
	@NoSpaces // Custom validation to disallow empty or whitespace-only values
	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@NotNull(message = "Position is required")
	@SizeRange(min = 2, max = 100, message = "Position should have between 2 and 100 characters") // Custom size
																									// validation
	@NoSpaces // Custom validation to disallow empty or whitespace-only values
	@Column(name = "position", nullable = false, length = 100)
	private String position;

	@ManyToOne
    @JoinColumn(
        name = "department_id", // Foreign key column in the Employee table
        referencedColumnName = "id", // Primary key column in the Department table
        foreignKey = @ForeignKey(name = "FK_EMP_DEPT") // Custom foreign key constraint name
    )
    private Dept department;
	
	@NotNull(message = "Employee code is required")
	@Column(name = "employee_code", nullable = false, unique = true, length = 100)
	private String employeeCode;
	
	@Transient
	private Integer departmentId;

    @PostLoad
    public void postLoad() {
        if (this.department != null) {
            this.departmentId = this.department.getId();
        }
    }
	public Employee() {
	}
	
	public Employee(String name, String position, Dept department, String employeeCode) {
		super();
		this.name = name;
		this.position = position;
		this.department = department;
		this.employeeCode = employeeCode;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Dept getDepartment() {
		return department;
	}

	public void setDepartment(Dept department) {
		this.department = department;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", position=" + position + ", department=" + department
				+ ", employeeCode=" + employeeCode + ", departmentId=" + departmentId + "]";
	}
}
