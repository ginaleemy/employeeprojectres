
-- Drop foreign key constraints first (example constraint names)
/*

DROP TABLE Employee_Project;
DROP TABLE project;
DROP TABLE Employee 
DROP TABLE Dept;



select * from dept;
select * from Employee ;
select * from project;
select  b.name,  a.* from EmployeeProject a, project b where a.project_id=b.id;

*/
-- Drop tables if they exist

-- Drop tables in reverse order of dependencies
--ALTER TABLE staff_project
--DROP CONSTRAINT FK5TC6V4VECRUVQNYOXPKTI12MU;

DROP TABLE IF EXISTS todos.employee_Project;
DROP TABLE IF EXISTS todos.project;
DROP TABLE IF EXISTS todos.employee;
DROP TABLE IF EXISTS todos.dept;


-- Create Department Table
CREATE TABLE project (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    project_status VARCHAR(2) NOT NULL
);
CREATE TABLE dept (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);
-- Create Employee Table
CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    position VARCHAR(100) NOT NULL,
    department_id INT,
    employee_code VARCHAR(100) NOT NULL UNIQUE,
    CONSTRAINT fk_department FOREIGN KEY (department_id) REFERENCES dept(id)
);
CREATE INDEX idx_employee_code ON Employee(employee_code);

-- Create Trigger to auto-generate employee_code
--CREATE TRIGGER before_insert_employee
--BEFORE INSERT ON Employee
--FOR EACH ROW
--CALL "com.employee.rest.webservices.restful_web_services01.trigger.EmployeeTrigger";


DELIMITER $$
CREATE TRIGGER before_employee_insert
BEFORE INSERT ON employee
FOR EACH ROW
BEGIN
    DECLARE new_id INT;

    -- Get the next available ID (assuming you are using auto-increment)
    SET new_id = (SELECT IFNULL(MAX(id), 0) + 1 FROM employee);

    -- Set the employee_code for the new row
    SET NEW.employee_code = CONCAT('EMP', LPAD(new_id, 4, '0'));
END $$
DELIMITER ;

-- Create Project Table
CREATE TABLE EmployeeProject (
    id INT AUTO_INCREMENT,
    project_id INT,
    employee_code VARCHAR(100) NOT NULL,
    PRIMARY KEY (employee_code, project_id),
    CONSTRAINT fk_employee  FOREIGN KEY (employee_code) REFERENCES Employee(employee_code),
    CONSTRAINT fk_project FOREIGN KEY (project_id) REFERENCES Project(id) 
);


