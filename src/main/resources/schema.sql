
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

DROP TABLE IF EXISTS employee_project;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS dept;


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


--Create Trigger to auto-generate employee_code
CREATE TRIGGER before_insert_employee
BEFORE INSERT ON Employee
FOR EACH ROW
CALL "com.employee.rest.api.trigger.EmployeeTrigger";


-- Create Project Table
CREATE TABLE employee_project (
    id INT AUTO_INCREMENT,
    project_id INT,
    employee_code VARCHAR(100) NOT NULL,
    PRIMARY KEY (employee_code, project_id),
    CONSTRAINT fk_employee  FOREIGN KEY (employee_code) REFERENCES Employee(employee_code),
    CONSTRAINT fk_project FOREIGN KEY (project_id) REFERENCES Project(id) 
);

INSERT INTO dept (name) VALUES 
    ('Information Technology'),
    ('Human Resources'),
    ('Finance'),
    ('Marketing'),
    ('Sales');

-- Insert Projects
INSERT INTO project (name, project_status) VALUES 
    ('Project Alpha', 'A'),
    ('Project Beta', 'A'),
    ('Project Gamma', 'C'),
    ('Project Delta', 'A'),
    ('Project Epsilon', 'A');

-- Insert Employees
INSERT INTO employee (name, position, department_id, employee_code) VALUES 
    ('John Doe', 'Software Engineer', 1, 'EMP0001'),
    ('Jane Smith', 'HR Manager', 2, 'EMP0002'),
    ('Michael Brown', 'Financial Analyst', 3, 'EMP0003'),
    ('Emily Davis', 'Marketing Specialist', 4, 'EMP0004'),
    ('James Wilson', 'Sales Executive', 5, 'EMP0005');


INSERT INTO employee (name, position, department_id, employee_code) VALUES ('John Doe', 'Software Engineer', 1, 'test');
-- Insert Staff Projects
INSERT INTO employee_project (project_id, employee_code) VALUES 
    ( 1, 'EMP0001'),
    ( 2, 'EMP0002'),
    ( 3, 'EMP0003'),
    ( 4, 'EMP0004'),
    ( 5, 'EMP0005');
